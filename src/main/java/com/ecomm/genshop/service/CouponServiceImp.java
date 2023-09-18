package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.CouponDao;
import com.ecomm.genshop.model.Coupon;

@Service
public class CouponServiceImp implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Override
    public Coupon save(Coupon coupon) {
        return couponDao.save(coupon);
    }

    @Override
    public void delete(Coupon coupon) {
        couponDao.delete(coupon);
        ;
    }

    @Override
    public List<Coupon> findAll() {
        return couponDao.findAll();
    }

    @Override
    public Optional<Coupon> findByCouponId(int couponId) {
        return couponDao.findById(couponId);
    }

    @Override
    public Optional<Coupon> findByCode(String code) {
        return couponDao.findByCouponCode(code);
    }

}
