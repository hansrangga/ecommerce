package com.ecomm.genshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.CartDao;
import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Cart;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public Optional<Cart> getCart(Account account) {
        throw new UnsupportedOperationException("Unimplemented method 'getCart'");
    }

    @Override
    public Cart createCart(Account account) {
        Cart newCart = new Cart();
        newCart.setUserId(account.getUserId());
        return newCart;
    }

    @Override
    public Cart findByAccount(Account account) {
        Cart tempCart = cartDao.findByUserId(account.getUserId());
        return tempCart;
    }

    @Override
    public void delete(Cart cart) {
        cartDao.deleteById(cart.getCartId());
    }

}
