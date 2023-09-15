package com.ecomm.genshop.DAO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Coupon;

@Repository
public interface CouponDao extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByCouponCode(String couponCode);

    List<Coupon> findByExpirationDateAfter(Date currentDate);

    List<Coupon> findByCouponStockLessThan(int stock);

}
