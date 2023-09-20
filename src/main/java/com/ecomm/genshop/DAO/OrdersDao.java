package com.ecomm.genshop.DAO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Orders;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer> {

    // Mencari semua Orders berdasarkan userId
    List<Orders> findByUserId(int userId);

    // Mencari Orders berdasarkan status
    List<Orders> findByStatus(String status);

    // Mencari Orders berdasarkan addressId
    List<Orders> findByAddressId(int addressId);

    // Mencari Orders berdasarkan paymentId
    List<Orders> findByPaymentId(int paymentId);

    // Menghitung total pemasanan berdasarkan userId
    int countByUserId(int userId);

    // Menghitung total nilai pemesanan berdasarkan userId
    BigDecimal sumTotalByUserId(int userId);

    // Memeriksa apakah ada Orders dengan status tertentu untuk userId tertentu
    boolean existsByUserIdAndStatus(int userId, String status);

    // Menghapus semua Orders berdasarkan userId
    void deleteByUserId(int userId);

    // Mengambil daftar pesanan berdasarkan rentang total harga
    List<Orders> findByTotalBetween(BigDecimal minTotal, BigDecimal maxTotal);

    // Mengambil daftar pesanan yang belum dibayar (misalnya status 'UNPAID')
    List<Orders> findByStatusOrderByOrderIdDesc(String status);

    // Mengambil daftar pesanan berdasarkan urutan dari yang terbaru
    List<Orders> findByOrderByOrderIdDesc();

    @Query(value = "SELECT o.* FROM orders o WHERE o.status = :status", nativeQuery = true)
    List<Orders> getAllOrders(String status);

    List<Orders> findByCreatedDateBetween(Date startDate, Date endDate);

}
