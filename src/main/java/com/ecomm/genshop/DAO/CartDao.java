package com.ecomm.genshop.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

    // Mencari Cart berdasarkan userId
    Optional<Cart> findByUserId(int userId);

    // Menghitung jumlah Cart berdasarkan userId
    long countByUserId(int userId);

    // Mencari Cart berdasarkan paymentId
    List<Cart> findByPaymentId(int paymentId);

    // Mencari semua Cart yang belum memiliki paymentId
    List<Cart> findByPaymentIdIsNull();

    // Mencari Cart berdasarkan couponId
    List<Cart> findByCouponId(int couponId);

    // Mencari semua Cart yang telah menggunakan kupon
    List<Cart> findByCouponIdIsNotNull();

    // Menghapus Cart berdasarkan userId
    void deleteByUserId(int userId);

}
