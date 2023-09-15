package com.ecomm.genshop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

    List<Address> findByUserId(int userId);

    List<Address> findByCityName(String cityName);

    List<Address> findByPostalCode(int postalCode);

}
