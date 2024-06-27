/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.ActivityResponse;
import com.epm.dto.response.ResponseStruct;
import com.epm.mapper.ActivityMapper;
import com.epm.pojo.Activity;
import com.epm.pojo.Faculty;
import com.epm.pojo.Semester;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.services.ActivityService;
import com.epm.services.SemesterService;
import com.epm.services.UserService;
import com.epm.utils.StatusResponse;
import com.epm.utils.TimestampConverter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api/activities")
@CrossOrigin
public class ApiActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SemesterService semesterService;
//    
//    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<List<Activity>> list() {
//        return new ResponseEntity<>(this.activityService.getActivities(), HttpStatus.OK);
//    }
//    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<List<Activity>> list() {
//        return new ResponseEntity<>(this.activityService.getActivities(), HttpStatus.OK);
//    }
    @Autowired
    private ActivityMapper activityMapper;

    @GetMapping(path = "/joined", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object[]>> listActivityJoining(@RequestParam Map<String, String> params) {
        Integer semesterId = null;
        Integer studentId = null;
        try {
            if (params.containsKey("semesterId") && params.get("semesterId") != null && !params.get("semesterId").isEmpty()) {
                semesterId = Integer.parseInt(params.get("semesterId"));
            }
            if (params.containsKey("studentId") && params.get("studentId") != null && !params.get("studentId").isEmpty()) {
                studentId = Integer.parseInt(params.get("studentId"));
            }

            List<Object[]> activities;

            Semester s = this.semesterService.findById(semesterId);
            String yearStudy = s.getYearStudy();
            User u = this.userService.findByStudentId(studentId);
            activities = this.activityService.getActivitiesJoined(u.getId(), semesterId, yearStudy);

            if (!activities.isEmpty()) {
                return new ResponseEntity<>(activities, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException | NullPointerException e) {
            List<Object[]> activities;
            if (semesterId != null) {
                Semester s = this.semesterService.findById(semesterId);
                String yearStudy = s.getYearStudy();
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = this.userService.getUserByUsername(auth.getName());
                activities = this.activityService.getActivitiesJoined(u.getId(), semesterId, yearStudy);
            } else if (studentId != null) {
                String yearStudy = params.get("yearStudy");
                User u = this.userService.findByStudentId(studentId);
                activities = this.activityService.getActivitiesJoined(u.getId(), 0, yearStudy);
            } else {
                String yearStudy = params.get("yearStudy");
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = this.userService.getUserByUsername(auth.getName());
                activities = this.activityService.getActivitiesJoined(u.getId(), 0, yearStudy);
            }

            if (!activities.isEmpty()) {
                return new ResponseEntity<>(activities, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/create", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createActivity(@RequestParam Map<String, String> data, @RequestPart MultipartFile file) throws ParseException {
        Semester semester = new Semester();
        Term term = new Term();
        Faculty faculty = new Faculty();
        String name = data.get("name");
        Timestamp startDate = TimestampConverter.convertStringToTimestamp(data.get("startDate"));
        Timestamp endDate = TimestampConverter.convertStringToTimestamp(data.get("endDate"));
        String description = data.get("description");
        int slots = Integer.parseInt(data.get("slots"));
        semester.setId(Integer.parseInt(data.get("semesterId")));
        term.setId(Integer.parseInt(data.get("termId")));
        faculty.setId(Integer.parseInt(data.get("facultyId")));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User createdUser = this.userService.getUserByUsername(auth.getName());

        Activity activity = new Activity(name, startDate, endDate, description, slots, faculty, semester, term, createdUser);
        activity.setFile(file);

        this.activityService.createActivity(activity);
    }

    @GetMapping(path = "/")
    public List<ActivityResponse> getActivity() {
        List<Activity> lists = this.activityService.getActivities();
        return lists.stream().map(activity -> activityMapper.toActivityResponse(activity)).collect(Collectors.toList());
    }
//    @GetMapping(path = "/")
//    public List<ActivityResponse> getActivity() {
//        List<Activity> lists = this.activityService.getActivities();
//        return lists.stream().map(activity -> activityMapper.toActivityResponse(activity)).collect(Collectors.toList());
//    }

//    @GetMapping(path = "/{activityId}")
//    public ActivityResponse getActivityById(@PathVariable("activityId") int activityId) {
//        Activity activity = this.activityService.findById(activityId);
//        return this.activityMapper.toActivityResponse(activity);
//    }
    @GetMapping("/{id}")
    public ResponseStruct<ActivityResponse> getActivityDetail(@PathVariable int id) {
        Activity activity = this.activityService.findById(id);
        if (activity == null) {
            return new ResponseStruct(StatusResponse.FAIL_RESPONSE, null);
        }

        return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, activityMapper.toActivityResponse(this.activityService.findById(id)));
    }

    @PostMapping(path = "/update/{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateActivity(@PathVariable int id, @RequestParam Map<String, String> data, @RequestPart(required = false) MultipartFile file) throws ParseException {
        Activity activity = this.activityService.findById(id);

        if (activity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }

        Semester semester = new Semester();
        Term term = new Term();
        Faculty faculty = new Faculty();

        String name = data.get("name");
        Timestamp startDate = TimestampConverter.convertStringToTimestamp(data.get("startDate"));
        Timestamp endDate = TimestampConverter.convertStringToTimestamp(data.get("endDate"));
        String description = data.get("description");
        int slots = Integer.parseInt(data.get("slots"));
        semester.setId(Integer.parseInt(data.get("semesterId")));
        term.setId(Integer.parseInt(data.get("termId")));
        faculty.setId(Integer.parseInt(data.get("facultyId")));

        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setDescription(description);
        activity.setSlots(slots);
        activity.setSemesterId(semester);
        activity.setTermId(term);
        activity.setFacultyId(faculty);

        if (file != null && !file.isEmpty()) {
            activity.setFile(file);
        }

        this.activityService.update(activity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable int id) {
        boolean isDeleted = activityService.deleteActivity(id);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> listAll(@RequestParam Map<String, String> params) {
        Integer semesterId = null;
        try {
            semesterId = Integer.parseInt(params.get("semesterId"));
            Semester s = this.semesterService.findById(semesterId);
            String yearStudy = s.getYearStudy();
            List<Object[]> activities = this.activityService.getAllActivities(semesterId, yearStudy);
            if (!activities.isEmpty()) {
                return new ResponseEntity<>(activities, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException | NullPointerException e) {
            String yearStudy = params.get("yearStudy");
            semesterId = 0;
            List<Object[]> activities = this.activityService.getAllActivities(semesterId, yearStudy);
            if (!activities.isEmpty()) {
                return new ResponseEntity<>(activities, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
