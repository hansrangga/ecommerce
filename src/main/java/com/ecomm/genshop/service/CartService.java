package com.ecomm.genshop.service;

import java.util.Optional;

import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Cart;
import com.ecomm.genshop.model.CartItem;

public interface CartService {

    Optional<Cart> getCart(Account account);

    Cart createCart(Account account);

    void addToCart(CartItem item);

    void updateCart(CartItem item, int quantity);

    void removeCart(CartItem item);

    void increaseQty(int cartItemId);

    void decreaseQty(int cartItemId);

    Cart findByAccount(Account account);

    void deleteCart(Cart cart);

    void deleteCartItem(Cart cart);

}
