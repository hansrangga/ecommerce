package com.ecomm.genshop.service;

import java.util.List;
import java.util.Optional;

import com.ecomm.genshop.model.Address;

public interface AddressService {

    void save(Address address);

    Optional<Address> findByAddressId(int addressId);

    List<Address> findByUserId(int userId);

    void delete(Address address);

}
