/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/")
public class ReportController {
    @Autowired ReportService reportService;
    
    @GetMapping("/report")
    public String reportSubmit(Model model){
        model.addAttribute("reports", this.reportService.getLists());
        
        System.out.println("--------");
        return "report";
    }
}
