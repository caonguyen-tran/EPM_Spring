/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.ActivityService;
import com.epm.services.ClassService;
import com.epm.services.JoinActivityService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
public class AdminJoiningController {
    @Autowired
    private JoinActivityService joinActivityService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClassService classService;

    @GetMapping(value = "/join")
    public String listJoining(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("activities", this.activityService.getActivities());
        model.addAttribute("classes", this.classService.getClasses());
        String activityId = params.get("activityId");
        String facultyId = params.get("facultyId");
        String classId = params.get("classId");
        String semesterId = params.get("semesterId");
        
        model.addAttribute("joins", this.joinActivityService.getParticipates(activityId, facultyId, classId, semesterId));
        return "join";
    }
}
