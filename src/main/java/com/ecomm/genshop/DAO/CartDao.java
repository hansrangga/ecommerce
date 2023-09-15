package com.ecomm.genshop.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

    // Mencari berdasarkan userId
    List<Cart> findByUserId(int userId);

    // Mencari berdasarkan productId
    List<Cart> findByProductId(int productId);

    // Mencari berdasarkan couponId
    List<Cart> findByCouponId(int couponId);

    // Mencari item keranjang berdasarkan userId dan productId (untuk mengecek
    // apakah produk tertentu sudah ada di keranjang pengguna atau belum)
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);

}
