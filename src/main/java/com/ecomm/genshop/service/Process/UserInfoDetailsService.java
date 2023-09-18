package com.ecomm.genshop.service.Process;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.AccountDao;
import com.ecomm.genshop.DAO.RoleDao;
import com.ecomm.genshop.DTO.AccountDTO;
import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Role;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    AccountDao accountDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No User Found with Email: " + email));

        return mapToDTO(account);
    }

    private AccountDTO mapToDTO(Account account) {
        Role roles = roleDao.findById(account.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found for username: " + account.getUserName()));
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setRoles(Collections.singletonList(roles.getRoleName()));

        return accountDTO;
    }

}
