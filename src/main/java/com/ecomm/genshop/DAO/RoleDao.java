package com.ecomm.genshop.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

}
