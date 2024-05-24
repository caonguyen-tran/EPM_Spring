/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Win11
 */
public interface CustomUserDetails extends UserDetails{
    String getUserId();
}
