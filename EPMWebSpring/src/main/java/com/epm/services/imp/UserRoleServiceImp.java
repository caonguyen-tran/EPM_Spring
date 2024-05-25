/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.UserRole;
import com.epm.repositories.UserRoleRepository;
import com.epm.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service
public class UserRoleServiceImp implements UserRoleService{
    @Autowired
    private UserRoleRepository userRoleRepo;

    @Override
    public UserRole getURStudent() {
        return this.userRoleRepo.getURStudent();
    }

    @Override
    public UserRole getURAssistant() {
        return this.userRoleRepo.getURAssistant();
    }

    @Override
    public UserRole getURAdmin() {
        return this.userRoleRepo.getURAdmin();
    }
    
}
