/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.AccountStudent;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
public interface AccountService {
    AccountStudent getUserByUsername(String username);
    boolean authUser(String username, String password);
    public AccountStudent addAccountStudent(Map<String, String> data, MultipartFile file);
}
