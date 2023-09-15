package com.ecomm.genshop.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {

}
