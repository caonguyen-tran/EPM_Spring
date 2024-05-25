/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.User;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface UserRepository {

    User getUserByUsername(String username);

    boolean authUser(String username, String password);

    void addUser(User user);

    List<User> getAssistantUsers();

    User findByStudentId(int studentId);
}
