/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.AccountStudent;
import com.epm.repositories.AccountRepository;
import com.epm.services.AccountService;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Override
    public AccountStudent getUserByUsername(String username) {
        return this.accountRepository.getUserByUsername(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.accountRepository.authUser(username, password);
    }

    @Override
    public AccountStudent addAccountStudent(AccountStudent accountStudent) {
        if(!accountStudent.getFile().isEmpty()){
            try {
                Map res = this.cloudinary.uploader().upload(accountStudent.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                accountStudent.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(AccountServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.accountRepository.addAccountStudent(accountStudent);
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
