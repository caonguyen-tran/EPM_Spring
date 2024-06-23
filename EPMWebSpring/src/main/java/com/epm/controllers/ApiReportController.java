/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.MissingReportService;
import com.epm.services.ReportService;
import com.epm.utils.CSVGenerator;
import com.epm.utils.PDFGenerator;
import com.epm.utils.StudentReportDTO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/report")
public class ApiReportController {

    @Autowired
    private ReportService reportService;

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
}
