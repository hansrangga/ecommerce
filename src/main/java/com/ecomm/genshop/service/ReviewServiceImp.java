package com.ecomm.genshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.ReviewDao;
import com.ecomm.genshop.model.Review;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    ReviewDao reviewDao;

    @Override
    public Review save(Review review) {
        return reviewDao.save(review);
    }

}
