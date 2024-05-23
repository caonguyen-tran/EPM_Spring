/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.AccountStudent;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Win11
 */
public interface AccountStudentService extends UserDetailsService{
    AccountStudent findByStudentId(int studentId);
    
    AccountStudent findByUsername(String username);
    
    void addAccountStudent(AccountStudent accountStudent);
    
    boolean authAccountStudent(String username, String password);
}
