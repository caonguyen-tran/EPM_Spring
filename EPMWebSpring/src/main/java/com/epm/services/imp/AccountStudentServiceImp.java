/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.AccountStudent;
import com.epm.repositories.AccountStudentRepository;
import com.epm.services.AccountStudentService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service("userDetailsService")
public class AccountStudentServiceImp implements AccountStudentService {

    @Autowired
    private AccountStudentRepository accountStudentRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public AccountStudent findByStudentId(int studentId) {
        return this.accountStudentRepo.findByStudentId(studentId);
    }

    @Override
    public AccountStudent findByUsername(String username) {
        return this.accountStudentRepo.findByUsername(username);
    }

    @Override
    public void addAccountStudent(AccountStudent accountStudent) {
        if (!accountStudent.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(accountStudent.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                accountStudent.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(AccountStudentServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.accountStudentRepo.addAccountStudent(accountStudent);
    }

    @Override
    public boolean authAccountStudent(String username, String password) {
        return this.accountStudentRepo.authAccountStudent(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountStudent accountStudent = this.accountStudentRepo.findByUsername(username);
        if (accountStudent == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(accountStudent.getUserRole()));

        return new org.springframework.security.core.userdetails.User(
                accountStudent.getUsername(), accountStudent.getPassword(), authorities);
    }

}
