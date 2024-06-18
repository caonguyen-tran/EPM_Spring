/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.components.JwtService;
import com.epm.dto.response.ResponseStruct;
import com.epm.dto.response.UserResponse;
import com.epm.mapper.UserMapper;
import com.epm.pojo.User;
import com.epm.services.UserService;
import com.epm.utils.StatusResponse;
import java.security.Principal;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin
public class ApiUserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserMapper userMapper;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStruct<String> loginSubmit(@RequestBody HashMap<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (this.userService.authUser(username, password) == true) {
            String token = this.jwtService.generateTokenLogin(username);

            return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, token);
        }

        return new ResponseStruct(StatusResponse.FAIL_RESPONSE, "none-token");
    }
    
    @GetMapping(path="/current-user")
    public ResponseStruct<UserResponse> getCurrentUser(Principal principal){
        String username = principal.getName();
        User user = this.userService.getUserByUsername(username);
        UserResponse userResponse = this.userMapper.toUserResponse(user);
        return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, userResponse);
    }
}
