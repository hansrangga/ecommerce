package com.ecomm.genshop.service;

import java.util.Optional;

import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Cart;

public interface CartService {

    Optional<Cart> getCart(Account account);

    Cart createCart(Account account);

    Cart findByAccount(Account account);

    void delete(Cart cart);

}
