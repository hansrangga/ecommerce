package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import com.ecomm.genshop.model.Coupon;

public interface CouponService {

    Coupon save(Coupon coupon);

    void delete(Coupon coupon);

    List<Coupon> findAll();

    Optional<Coupon> findByCouponId(int couponId);

    Optional<Coupon> findByCode(String code);

}
