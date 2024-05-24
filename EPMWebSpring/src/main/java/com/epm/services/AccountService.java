/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.AccountStudent;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ACER
 */
public interface AccountService extends UserDetailsService{
    AccountStudent getUserByUsername(String username);
    boolean authUser(String username, String password);
    public AccountStudent addAccountStudent(AccountStudent accountStudent);
}
