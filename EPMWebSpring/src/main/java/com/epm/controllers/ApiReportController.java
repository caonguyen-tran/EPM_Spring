/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Activity;
import com.epm.pojo.MissingReport;
import com.epm.pojo.User;
import com.epm.services.ActivityService;
import com.epm.services.FacultyService;
import com.epm.services.MissingReportService;
import com.epm.services.ReportService;
import com.epm.services.UserService;
import com.epm.utils.CSVGenerator;
import com.epm.utils.PDFGenerator;
import com.epm.utils.StatusReport;
import com.epm.utils.StudentReportDTO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/report")
public class ApiReportController {
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private MissingReportService missingReportService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/pdf/{studentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdfReport(@PathVariable int studentId) throws IOException {
        List<StudentReportDTO> reports = reportService.getStatsScoreDetail(studentId);
        ByteArrayInputStream bis = PDFGenerator.generatePdfReport(reports);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=student_report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/csv/{studentId}", produces = "text/csv")
    public ResponseEntity<InputStreamResource> generateCsvReport(@PathVariable int studentId) {
        List<StudentReportDTO> reports = reportService.getStatsScoreDetail(studentId);
        ByteArrayInputStream bis = CSVGenerator.generateCsvReport(reports);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=student_report.csv");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("text/csv")).body(new InputStreamResource(bis));
    }
    
    @GetMapping(path = "/")
    public ResponseEntity<List<Object[]>> listMissingReports(@RequestParam HashMap<String, String> params){
        int facultyId = 0;
        if(!params.isEmpty()){
            facultyId = Integer.parseInt(params.get("facultyId"));
        }
        
        List<Object[]> lists = this.missingReportService.getListMissingReports(facultyId);
        return new ResponseEntity(lists, HttpStatus.OK);
    }
    
    @PostMapping(path = "/", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createMissingReport(@RequestBody HashMap<String, String> params,@RequestPart MultipartFile file){
        int activityId = Integer.parseInt(params.get("activityId"));
        String note = params.get("note");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUserByUsername(auth.getName());
        
        Activity activity = this.activityService.findById(activityId);
        
        MissingReport missingReport = new MissingReport(StatusReport.WAITING, note, user, activity);
        missingReport.setFile(file);
        
        this.missingReportService.addMissingReport(missingReport);
    }
}
