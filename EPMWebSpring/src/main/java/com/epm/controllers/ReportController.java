/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/")
public class ReportController {
    
    @PostMapping("/report")
    public String reportSubmit(@RequestParam Map<String, String> map){
        System.out.println("hello");
        System.out.println(map.get("email"));
        return "report";
    }
}
