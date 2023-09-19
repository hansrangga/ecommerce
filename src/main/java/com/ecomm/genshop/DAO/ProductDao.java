package com.ecomm.genshop.DAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Product findByProductName(String productName);

    // Mencari berdasarkan productName
    List<Product> findByProductNameContainingIgnoreCase(String productName);

    // Mencari berdasarkan category
    List<Product> findByCategory(String category);

    // Mencari produk dengan stok rendah
    List<Product> findByStockLessThan(int stockThreshold);

    // Mencari produk dengan stok lebih dari batas tertentu
    List<Product> findByStockGreaterThan(int stockThreshold);

    // Mencari produk berdasarkan rentang harga
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Mencari produk berdasarkan rentang harga grosir
    List<Product> findByWholesalePriceBetween(BigDecimal minWholesalePrice, BigDecimal maxWholesalePrice);

    // Mencari produk berdasarkan rentang harga penawaran
    List<Product> findByOfferPriceBetween(BigDecimal minOfferPrice, BigDecimal maxOfferPrice);

    // Mencari produk yang memiliki harga penawaran
    List<Product> findByOfferPriceIsNotNull();

}