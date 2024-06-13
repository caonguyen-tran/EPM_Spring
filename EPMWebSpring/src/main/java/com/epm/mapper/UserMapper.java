/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.UserResponse;
import com.epm.pojo.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class UserMapper {
    
    public UserResponse toUserResponse(User user){
        UserResponse ur = new UserResponse();
        ur.setId(user.getId());
        ur.setUsername(user.getUsername());
        ur.setActive(user.getActive());
        ur.setAvatar(user.getAvatar());
        ur.setUserRole(user.getUserRoleId());
        return ur;
    }
}
