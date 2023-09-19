package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import com.ecomm.genshop.model.Product;

public interface ProductService {

    Product getByProductName(String productName);

    Product save(Product product);

    List<Product> getAll();

    Optional<Product> findByProductId(int productId);

    void delete(int productId);

}
