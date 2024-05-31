/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Activity;
import com.epm.pojo.Faculty;
import com.epm.pojo.Semester;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.services.ActivityService;
import com.epm.services.UserService;
import com.epm.utils.TimestampConverter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api/activities")
public class ApiActivityController {
    @Autowired
    private UserService userService;

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
    
    @PostMapping(path = "/create", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createActivity(@RequestBody Map<String, String> data, @RequestPart MultipartFile file) throws ParseException{
        Semester semester = new Semester();
        Term term = new Term();
        Faculty faculty = new Faculty();
        String name = data.get("name");
        Timestamp startDate = TimestampConverter.convertStringToTimestamp(data.get("startDate"));
        Timestamp endDate = TimestampConverter.convertStringToTimestamp(data.get("endDate"));
        String description = data.get("description");
        int slots = Integer.parseInt(data.get("slots"));
        semester.setId(Integer.parseInt(data.get("semesterId")));
        term.setId(Integer.parseInt(data.get("termId")));
        faculty.setId(Integer.parseInt(data.get("facultyId")));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User createdUser = this.userService.getUserByUsername(auth.getName());

        Activity activity = new Activity(name, startDate, endDate, description, slots, faculty, semester, term, createdUser);
        activity.setFile(file);

        this.activityService.createActivity(activity);
    }
}
