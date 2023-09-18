package com.ecomm.genshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.RoleDao;
import com.ecomm.genshop.model.Role;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

}
