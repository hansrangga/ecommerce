package com.ecomm.genshop.DAO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Orders;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer> {

    // Mencari berdasarkan userId
    List<Orders> findByUserId(int userId);

    // Mencari berdasarkan status
    List<Orders> findByStatus(String status);

    // Mencari berdasarkan productId
    List<Orders> findByProductId(int productId);

    // Mencari pesanan berdasarkan userId dan productId (untuk mengecek apakah
    // pengguna telah memesan produk tertentu sebelumnya)
    Optional<Orders> findByUserIdAndProductId(int userId, int productId);

    // Mencari pesanan berdasarkan userId dan status
    List<Orders> findByUserIdAndStatus(int userId, String status);

    // Mencari pesanan berdasarkan rentang harga total
    List<Orders> findByTotalBetween(BigDecimal minTotal, BigDecimal maxTotal);

}
