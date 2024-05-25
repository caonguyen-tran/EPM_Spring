/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Activity;
import com.epm.services.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api/activities")
public class ApiActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Activity>> list() {
        return new ResponseEntity<>(this.activityService.getActivities(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Activity>> listActivityJoining(@PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(this.activityService.getActivitiesJoined(userId), HttpStatus.OK);
    }
    
    @GetMapping(path = "/missing/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Activity>> getActivitiesMissing(@PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(this.activityService.getActivitiesMissingByUserId(userId), HttpStatus.OK);
    }
}
