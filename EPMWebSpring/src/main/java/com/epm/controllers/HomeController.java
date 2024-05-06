/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.ActivityService;
import com.epm.services.FacultyService;
import com.epm.services.SemesterService;
import com.epm.services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ACER
 */
@Controller
@ControllerAdvice
public class HomeController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("activities", this.activityService.getActivities());

        return "index";
    }

    @RequestMapping("/report")
    public String missingReport(Model model) {
        return "report";
    }

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
        model.addAttribute("semesters", semesterService.getSemesters());
    }
}
