package com.ecomm.genshop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.CartItem;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {

    // Mencari semua CartItem berdasarkan cartId
    List<CartItem> findByCartId(int cartId);

    // Mencari semua CartItem berdasarkan productId
    List<CartItem> findByProductId(int productId);

    // Menghitung jumlah total item dalam sebuah cart berdasarkan cartId
    int countByCartId(int cartId);

    // Menghitung total quantity dari sebuah produk di semua cart
    int sumQuantityByProductId(int productId);

    // Memeriksa apakah sebuah produk ada di cart tertentu
    boolean existsByCartIdAndProductId(int cartId, int productId);

    // Menghapus semua item di sebuah cart berdasarkan cartId
    void deleteByCartId(int cartId);

    // Menghapus sebuah produk dari semua cart berdasarkan productId
    void deleteByProductId(int productId);

}
