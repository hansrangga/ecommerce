package com.ecomm.genshop.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, Integer> {

    // Mencari berdasarkan userId
    List<Review> findByUserId(int userId);

    // Mencari berdasarkan productId
    List<Review> findByProductId(int productId);

    // Mencari berdasarkan rating
    List<Review> findByRating(short rating);

    // Mencari ulasan berdasarkan rentang tanggal posting
    List<Review> findByDatePostedBetween(Date startDate, Date endDate);

}
