/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.UserRole;

/**
 *
 * @author Win11
 */
public interface UserRoleService {
    UserRole getURStudent();
    UserRole getURAssistant();
    UserRole getURAdmin();
}
