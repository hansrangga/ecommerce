package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.AddressDao;
import com.ecomm.genshop.model.Address;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public void save(Address address) {
        addressDao.save(address);
    }

    @Override
    public Optional<Address> findByAddressId(int addressId) {
        return addressDao.findById(addressId);
    }

    @Override
    public List<Address> findByUserId(int userId) {
        return addressDao.findByUserId(userId);
    }

    @Override
    public void delete(Address address) {
        address.setDeleted(true);
        addressDao.save(address);
    }

}
