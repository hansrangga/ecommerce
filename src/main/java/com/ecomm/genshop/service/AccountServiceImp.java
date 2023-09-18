package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.AccountDao;
import com.ecomm.genshop.model.Account;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findByUserId(int userId) {
        return accountDao.findById(userId).orElse(null);
    }

    @Override
    public void delete(int userId) {
        accountDao.deleteById(userId);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Optional<Account> findByUserName(String userName) {
        return accountDao.findByUserName(userName);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountDao.findByEmail(email);
    }

}
