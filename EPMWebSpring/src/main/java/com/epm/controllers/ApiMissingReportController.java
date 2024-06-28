/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Activity;
import com.epm.pojo.MissingReport;
import com.epm.pojo.Semester;
import com.epm.pojo.User;
import com.epm.services.ActivityService;
import com.epm.services.MissingReportService;
import com.epm.services.SemesterService;
import com.epm.services.UserService;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/missing-report")
public class ApiMissingReportController {

    @Autowired
    private UserService userService;

    @Autowired
    private MissingReportService missingReportService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/create", consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createMissingReport(@RequestParam HashMap<String, String> params, @RequestPart MultipartFile[] file) {
        MissingReport mr = new MissingReport();
        mr.setStatus("Pending");
        if (file.length > 0) {
            mr.setFile(file[0]);
        }
        mr.setNote(params.get("note"));
        mr.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(auth.getName());
        mr.setUserId(u);

        int acId = Integer.parseInt(params.get("activityId"));
        Activity activity = this.activityService.findById(acId);

        mr.setActivityId(activity);

        this.missingReportService.addMissingReport(mr);
    }

    @GetMapping(path = "/get-missing-report-of-student/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> getMissingReportsOfStudent(@RequestParam HashMap<String, String> params) {
        Integer semesterId = null;
        Integer studentId = null;
        try{
            if (params.containsKey("semesterId") && params.get("semesterId") != null && !params.get("semesterId").isEmpty()) {
                semesterId = Integer.parseInt(params.get("semesterId"));
            }
            if (params.containsKey("studentId") && params.get("studentId") != null && !params.get("studentId").isEmpty()) {
                studentId = Integer.parseInt(params.get("studentId"));
            }

            List<Object[]> listMROS;

            Semester s = this.semesterService.findById(semesterId);
            String yearStudy = s.getYearStudy();
            User u = this.userService.findByStudentId(studentId);
            listMROS = this.missingReportService.getListMRByStudent(u.getId(), semesterId, yearStudy);

            if (!listMROS.isEmpty()) {
                return new ResponseEntity<>(listMROS, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException | NullPointerException e) {
            String yearStudy = params.get("yearStudy");
            List<Object[]> listMROS;
            if (semesterId != null) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = this.userService.getUserByUsername(auth.getName());
                listMROS = this.missingReportService.getListMRByStudent(u.getId(), semesterId, yearStudy);
            } else if (studentId != null){
                User u = this.userService.findByStudentId(studentId);
                listMROS = this.missingReportService.getListMRByStudent(u.getId(), 0, yearStudy);
            } else {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = this.userService.getUserByUsername(auth.getName());
                listMROS = this.missingReportService.getListMRByStudent(u.getId(), 0, yearStudy);
            }

            if (!listMROS.isEmpty()) {
                return new ResponseEntity<>(listMROS, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
    }

    @GetMapping(path = "/get-list", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> listMissingReport(@RequestParam HashMap<String, String> params) {
        Integer semesterId = null;
        try {
            semesterId = Integer.parseInt(params.get("semesterId"));
            Semester s = this.semesterService.findById(semesterId);
            String yearStudy = s.getYearStudy();
            List<Object[]> listMROS = this.missingReportService.listMissingReport(semesterId, yearStudy);
            if (!listMROS.isEmpty()) {
                return new ResponseEntity<>(listMROS, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException | NullPointerException e) {
            String yearStudy = params.get("yearStudy");
            semesterId = 0;
            List<Object[]> listMROS = this.missingReportService.listMissingReport(semesterId, yearStudy);
            if (!listMROS.isEmpty()) {
                return new ResponseEntity<>(listMROS, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/faculty/{facultyId}")
    public ResponseEntity<List<Object[]>> listMissingReports(@PathVariable(value = "facultyId") int facultyId) {
        List<Object[]> lists = this.missingReportService.getListMissingReports(facultyId);
        return new ResponseEntity(lists, HttpStatus.OK);
    }
    
    @GetMapping(path = "/{mrId}")
    public ResponseEntity<List<Object[]>> getMRById(@PathVariable(value = "mrId") int mrId) {
        Object[] missingReport = this.missingReportService.getMRById(mrId);
        return new ResponseEntity(missingReport, HttpStatus.OK);
    }
}
