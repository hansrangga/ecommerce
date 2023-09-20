package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.CategoryDao;
import com.ecomm.genshop.model.Category;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public Category getByCategoryName(String categoryName) {
        return categoryDao.getByCategoryName(categoryName);
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) {
        return categoryDao.findByCategoryName(categoryName);
    }

    @Override
    public List<Category> searchCategoriesByName(String keyword) {
        return categoryDao.findByCategoryNameContainingIgnoreCase(keyword);
    }

    @Override
    public boolean doesCategoryExistByName(String categoryName) {
        return categoryDao.existsByCategoryName(categoryName);
    }

    @Override
    public void removeCategoryByName(String categoryName) {
        categoryDao.deleteByCategoryName(categoryName);
    }

    @Override
    public List<String> getAllCategoryNames() {
        return categoryDao.findAllCategoryNames();
    }

    @Override
    public List<Category> getAllCategoriesInAlphabeticalOrder() {
        return categoryDao.findByOrderByCategoryNameAsc();
    }

    @Override
    public long getTotalNumberOfCategories() {
        return categoryDao.countAllCategories();
    }

}
