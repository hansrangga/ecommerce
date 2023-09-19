package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.ProductDao;
import com.ecomm.genshop.model.Product;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getByProductName(String productName) {
        return productDao.findByProductName(productName);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> findByProductId(int productId) {
        return productDao.findById(productId);
    }

    @Override
    public void delete(int productId) {
        productDao.deleteById(productId);
    }

}
