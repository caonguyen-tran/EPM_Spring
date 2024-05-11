/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.AccountStudent;

/**
 *
 * @author ACER
 */
public interface AccountRepository {
    AccountStudent getUserByUsername(String username);
    boolean authUser(String username, String password);
    public AccountStudent addAccountStudent(AccountStudent accountStudent);
}
