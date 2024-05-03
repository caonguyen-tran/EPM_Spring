/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Activity;
import com.epm.services.ActivityService;
import com.epm.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ACER
 */
@Controller
public class HomeController {
    @Autowired
    private ActivityService activityService;
    
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("activities", this.activityService.getActivities());
        System.out.println("hello");
        return "index";  
    }
    
    @RequestMapping("/report")
    public String missingReport(Model model){
        return "report";
    }
}
