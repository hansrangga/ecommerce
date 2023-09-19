package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.CartDao;
import com.ecomm.genshop.DAO.CartItemDao;
import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Cart;
import com.ecomm.genshop.model.CartItem;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public Optional<Cart> getCart(Account account) {
        Optional<Cart> existingCart = cartDao.findByUserId(account.getUserId());
        if (existingCart.isPresent()) {
            return existingCart;
        } else {
            Cart newCart = createCart(account);
            cartDao.save(newCart);
            return Optional.of(newCart);
        }
    }

    @Override
    public Cart createCart(Account account) {
        Cart newCart = new Cart();
        newCart.setUserId(account.getUserId());
        return newCart;
    }

    @Override
    public void addToCart(CartItem item) {
        int cartId = item.getCartId();
        Cart cart = cartDao.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found for ID: " + cartId));

        Optional<CartItem> existingItem = cartItemDao.findByCartId(cartId).stream()
                .filter(cartItem -> cartItem.getProductId() == item.getProductId())
                .findFirst();

        if (existingItem.isPresent()) {
            updateCart(existingItem.get(), existingItem.get().getQuantity() + 1);
        } else {
            item.setQuantity(1);
            cartItemDao.save(item);
        }
        cartDao.save(cart);
    }

    @Override
    public void updateCart(CartItem item, int quantity) {
        item.setQuantity(quantity);
        cartItemDao.save(item);
    }

    @Override
    public void removeCart(CartItem item) {
        cartItemDao.delete(item);
    }

    @Override
    public void increaseQty(int cartItemId) {
        CartItem item = cartItemDao.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found for ID: " + cartItemId));
        item.setQuantity(item.getQuantity() + 1);
        cartItemDao.save(item);
    }

    @Override
    public void decreaseQty(int cartItemId) {
        CartItem item = cartItemDao.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found for ID: " + cartItemId));
        int newQuantity = item.getQuantity() - 1;
        if (newQuantity > 0) {
            item.setQuantity(newQuantity);
            cartItemDao.save(item);
        } else {
            cartItemDao.delete(item);
        }
    }

    @Override
    public Cart findByAccount(Account account) {
        return cartDao.findByUserId(account.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + account.getUserId()));
    }

    @Override
    public void deleteCart(Cart cart) {
        cartDao.delete(cart);
    }

    @Override
    public void deleteCartItem(Cart cart) {
        List<CartItem> items = cartItemDao.findByCartId(cart.getCartId());
        for (CartItem item : items) {
            cartItemDao.deleteById(item.getCartItemId());
        }
        cartDao.deleteById(cart.getCartId());
    }

}
