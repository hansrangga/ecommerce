package com.ecomm.genshop.DAO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

    Optional<Account> findByUserName(String userName);

    Optional<Account> findByEmail(String email);

    List<Account> findByRoleId(int roleId);

    List<Account> findByRegistrationDateBetween(Date startDate, Date endDate);
}
