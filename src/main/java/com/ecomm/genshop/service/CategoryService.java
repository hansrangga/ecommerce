package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import com.ecomm.genshop.model.Category;

public interface CategoryService {

    void save(Category category);

    Category getByCategoryName(String categoryName);

    Optional<Category> findByCategoryName(String categoryName);

    List<Category> searchCategoriesByName(String keyword);

    boolean doesCategoryExistByName(String categoryName);

    void removeCategoryByName(String categoryName);

    List<String> getAllCategoryNames();

    List<Category> getAllCategoriesInAlphabeticalOrder();

    long getTotalNumberOfCategories();

}
