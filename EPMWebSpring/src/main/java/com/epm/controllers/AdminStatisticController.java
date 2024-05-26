/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ACER
 */
@Controller
public class AdminStatisticController {
    @Autowired
    private FacultyService facultyService;
    
    @RequestMapping("/dashboard")
    public String dashboardSite(Model model){
        model.addAttribute("faculties", this.facultyService.getFaculties());
        return "dashboard";
    }
}
