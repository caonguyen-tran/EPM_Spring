/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.MissingReport;
import com.epm.services.MissingReportService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api")
public class ApiMissingReportController {

    @Autowired
    private MissingReportService missingReportService;

    @PostMapping(path = "/missing-report/create/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void createMissingReport(@Valid @RequestBody MissingReport mr, BindingResult res) {
//        if(file.length > 0){
//            mr.setFile(file[0]);
//        }
//        
//        if (!res.hasErrors()) {
//            this.missingReportService.createMissingReport(mr);
//        } else {
//            System.err.print(res);
//        }
        this.missingReportService.createMissingReport(mr);
    }
}
