package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import com.ecomm.genshop.model.Account;

public interface AccountService {

    Account findByUserId(int userId);

    void delete(int userId);

    List<Account> findAll();

    Optional<Account> findByUserName(String userName);

    Optional<Account> findByEmail(String email);

}