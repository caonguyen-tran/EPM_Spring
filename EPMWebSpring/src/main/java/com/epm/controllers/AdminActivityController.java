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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Controller
public class AdminActivityController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/activity")
    public String registerSite(Model model) {
        model.addAttribute("activity", new Activity());

        return "activity";
    }

    @PostMapping("/activity")
    public String registerSubmit(@RequestParam HashMap<String, String> data, @RequestPart MultipartFile file) throws ParseException {
        Semester semester = new Semester();
        Term term = new Term();
        Faculty faculty = new Faculty();
        String name = data.get("name");
        Timestamp startDate = convertStringToTimestamp(data.get("startDate"));
        Timestamp endDate = convertStringToTimestamp(data.get("endDate"));
        String description = data.get("description");
        int slots = Integer.parseInt(data.get("slots"));
        semester.setId(Integer.parseInt(data.get("semesterId")));
        term.setId(Integer.parseInt(data.get("termId")));
        faculty.setId(Integer.parseInt(data.get("facultyId")));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User createdUser = this.userService.getUserByUsername(auth.getName());

        Activity activity = new Activity();
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setDescription(description);
        activity.setSlots(slots);
        activity.setFacultyId(faculty);
        activity.setTermId(term);
        activity.setSemesterId(semester);
        activity.setCreatedUserId(createdUser);
        activity.setFile(file);

        this.activityService.createActivity(activity);
        return "activity";
    }

    public Timestamp convertStringToTimestamp(String date) throws ParseException {
        String pattern = "yyyy-MM-dd'T'HH:mm";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        java.util.Date parsedDate = dateFormat.parse(date);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        return timestamp;
    }
}
