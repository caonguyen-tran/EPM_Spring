/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.ReportService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/")
public class ReportController {
    @Autowired ReportService reportService;
    
    @GetMapping("/report")
    public String listReports(Model model, @RequestParam HashMap<String, String> data){
        int facultyId = 0;
        if(!data.isEmpty()){
            facultyId = Integer.parseInt(data.get("facultyId"));
        }
        model.addAttribute("reports", this.reportService.getListReports(facultyId));
        
        System.out.println("--------");
        return "report";
    }
}
