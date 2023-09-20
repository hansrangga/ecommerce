package com.ecomm.genshop.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Category getByCategoryName(String categoryName);

    // Mencari Category berdasarkan categoryName
    Optional<Category> findByCategoryName(String categoryName);

    // Mencari Category yang namanya mengandung kata kunci tertentu
    List<Category> findByCategoryNameContainingIgnoreCase(String keyword);

    // Mencari Category berdasarkan categoryDesc
    List<Category> findByCategoryDescContainingIgnoreCase(String keyword);

    // Memeriksa apakah ada Category dengan nama tertentu
    boolean existsByCategoryName(String categoryName);

    // Menghapus Category berdasarkan categoryName
    void deleteByCategoryName(String categoryName);

    // Mengambil daftar semua nama kategori
    @Query("SELECT c.categoryName FROM Category c")
    List<String> findAllCategoryNames();

    // Mengambil daftar kategori berdasarkan urutan abjad dari nama kategori
    List<Category> findByOrderByCategoryNameAsc();

    // Memeriksa apakah ada kategori dengan deskripsi tertentu
    boolean existsByCategoryDesc(String categoryDesc);

    // Mengambil jumlah total kategori yang ada
    @Query("SELECT COUNT(c) FROM Category c")
    long countAllCategories();

}
