/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Win11
 */
public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);

    void addUser(User user);

    boolean authUser(String username, String password);

    List<User> getAssistantUsers();

    User findByStudentId(int studentId);

    void sendVerificationEmail(User user, String siteURL, String email);

    User findByVerificationCode(String verificationCode);

    boolean verify(String verificationCode);

    void save(User u);
    
    void update(User u);
    
    User findByEmail(String email);
    
    User findById(int id);
}
