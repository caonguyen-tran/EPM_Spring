/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.MissingReport;
import com.epm.services.MissingReportService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api")
public class ApiMissingReportController {
    @Autowired
    private MissingReportService missingReportService;
    
    @GetMapping(path = "/missing-report/{accountStudentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<MissingReport>> getMissingReportByAccountStudentId(@PathVariable(value = "accountStudentId") int accountStudentId){
        return new ResponseEntity<>(this.missingReportService.getMissingReportByAccountStudentId(accountStudentId), HttpStatus.OK);
    }
    
    @PostMapping(path = "/missing-report/create/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void createMissingReport(@Valid @RequestBody MissingReport mr, BindingResult res, HttpServletRequest request){
        if(!res.hasErrors()){
            this.missingReportService.createMissingReport(mr);
        } else{
            System.err.print(res);
        }
    }
}
