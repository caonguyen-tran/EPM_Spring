/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.AccountStudent;
import com.epm.pojo.Student;
import com.epm.repositories.AccountRepository;
import com.epm.services.AccountService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public AccountStudent getUserByUsername(String username) {
        return this.accountRepository.getUserByUsername(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.accountRepository.authUser(username, password);
    }

    @Override
    public AccountStudent addAccountStudent(Map<String, String> data, MultipartFile file) {
        return null;
    }
    
}
