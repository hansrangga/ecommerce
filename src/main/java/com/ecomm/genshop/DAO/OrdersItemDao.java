package com.ecomm.genshop.DAO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.OrdersItem;

@Repository
public interface OrdersItemDao extends JpaRepository<OrdersItem, Integer> {

    // Mencari semua OrdersItem berdasarkan orderId
    List<OrdersItem> findByOrderId(int orderId);

    // Mencari semua OrdersItem berdasarkan productId
    List<OrdersItem> findByProductId(int productId);

    // Menghitung jumlah total item yang dipesan berdasarkan orderId
    int countByOrderId(int orderId);

    // Menghitung total quantity dari sebuah produk di semua pesanan
    int sumQuantityByProductId(int productId);

    // Menghapus semua item pesanan berdasarkan orderId
    void deleteByOrderId(int orderId);

    // Mengambil semua item pesanan berdasarkan rentang harga
    List<OrdersItem> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Mengambil rata-rata harga dari semua OrdersItem berdasarkan orderId
    @Query("SELECT AVG(oi.price) FROM OrdersItem oi WHERE oi.orderId = ?1")
    BigDecimal findAveragePriceByOrderId(int orderId);

    // Mengambil total harga (jumlah) dari semua OrdersItem berdasarkan orderId
    @Query("SELECT SUM(oi.price) FROM OrdersItem oi WHERE oi.orderId = ?1")
    BigDecimal findTotalPriceByOrderId(int orderId);

    // Mengambil OrdersItem dengan kuantitas tertinggi berdasarkan orderId
    @Query("SELECT oi FROM OrdersItem oi WHERE oi.orderId = ?1 ORDER BY oi.quantity DESC")
    Optional<OrdersItem> findWithHighestQuantityByOrderId(int orderId);

}
