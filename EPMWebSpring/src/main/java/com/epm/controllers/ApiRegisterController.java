/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.JoinActivityResponse;
import com.epm.dto.response.ResponseStruct;
import com.epm.pojo.Activity;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.User;
import com.epm.services.RegisterService;
import com.epm.utils.StatusResponse;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api/register")
@CrossOrigin
public class ApiRegisterController {
    @Autowired
    private RegisterService registerService;

    @GetMapping(path = "/")
    public List<JoinActivityResponse> getRegisterByUser() {
        return null;
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStruct<String> submidRegister(@RequestBody HashMap<String, String> req) {
        int activityId = Integer.parseInt(req.get("activityId"));
        int userId = Integer.parseInt(req.get("userId"));
        
        Activity activity = new Activity();
        activity.setId(activityId);
        User user = new User();
        user.setId(userId);
        
        JoinActivity joinActivity = new JoinActivity();
        joinActivity.setActivityId(activity);
        joinActivity.setUserId(user);
        
        this.registerService.submitRegister(joinActivity);
        return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, "SUCCESSFULLY");
    }
    
    @GetMapping(path="/personal-register/{userId}")
    public ResponseStruct<List<Object[]>> getRegisterByUser(@PathVariable(value="userId") int userId){
        return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, this.registerService.getRegistersByUser(userId));
    }
    
    @DeleteMapping(path="/{registerId}")
    public ResponseStruct<String> removeRegister(@PathVariable(value="registerId") int registerId, @RequestBody HashMap<String, String> data){
        int userId = Integer.parseInt(data.get("userId"));
        JoinActivity joinActivity = this.registerService.getRegisterById(registerId);
        if(userId == joinActivity.getUserId().getId()){
            this.registerService.removeRegister(joinActivity);
            return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, HttpStatus.NO_CONTENT);
        }
        return new ResponseStruct(StatusResponse.FAIL_RESPONSE, HttpStatus.UNAUTHORIZED);
    }
}
