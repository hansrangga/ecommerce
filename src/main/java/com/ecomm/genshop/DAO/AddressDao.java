package com.ecomm.genshop.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

    Optional<Address> findFirstByUserId(int userId);

    List<Address> findByUserId(int userId);

    List<Address> findByCityName(String cityName);

    List<Address> findByPostalCode(int postalCode);

    List<Address> findAll();
}
