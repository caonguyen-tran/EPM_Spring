/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.JoinActivityResponse;
import com.epm.mapper.JoinActivityMapper;
import com.epm.pojo.JoinActivity;
import com.epm.services.JoinActivityService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api")
public class ApiJoinActivityController {
    @Autowired
    private JoinActivityService joinActivityService;
    
    private final JoinActivityMapper joinActivityMapper = new JoinActivityMapper();
    
    @DeleteMapping("/join-activity/{joinId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectJoin(@PathVariable(value = "joinId") int joinId){
        JoinActivity j = new JoinActivity();
        j.setId(joinId);
        System.out.println(joinId);
        this.joinActivityService.deleteJoinActivity(j);
    }
    
    @GetMapping("/join-activity/{activityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<JoinActivityResponse> getJoinActivityByActivityId(@PathVariable(value = "activityId") int activityId){
        List<JoinActivity> lists = this.joinActivityService.getJoinActivityByActivityId(activityId);
        return lists.stream().map(joinActivity -> joinActivityMapper.toJoinActivity(joinActivity)).collect(Collectors.toList());
    }
}
