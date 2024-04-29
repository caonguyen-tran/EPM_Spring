/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Activity;
import com.epm.services.FacultyService;
import com.epm.services.SemesterService;
import com.epm.services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ACER
 */
@Controller
public class RegisterController {

    @Autowired
    private TermService termService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private SemesterService semesterService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("terms", termService.getTerms());
        model.addAttribute("faculties", facultyService.getFaculties());
    }

    @GetMapping("/register")
    public String registerSite(Model model) {
        model.addAttribute("activity", new Activity());
        model.addAttribute("semesters", semesterService.getSemesters());

        return "register";
    }
    
//    
//    @PostMapping
//    public String createRegister(@RequestBody){
//        
//        
//        return "index";
//    }
}
