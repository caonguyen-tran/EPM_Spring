/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.ActivityResponse;
import com.epm.mapper.ActivityMapper;
import com.epm.pojo.Activity;
import com.epm.pojo.Faculty;
import com.epm.pojo.Semester;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.services.ActivityService;
import com.epm.services.SemesterService;
import com.epm.services.UserService;
import com.epm.utils.TimestampConverter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
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
public class ApiActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SemesterService semesterService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Activity>> list() {
        return new ResponseEntity<>(this.activityService.getActivities(), HttpStatus.OK);
    }
  
    @Autowired
    private ActivityMapper activityMapper;

    @GetMapping(path = "/joined", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> listActivityJoining(@RequestParam Map<String, String> params) {
        List<Semester> s = this.semesterService.findBySemesterName(params.get("semester"));
        String yearStudy = params.get("yearStudy");
        int semesterId = 0;
        for (Semester semester : s) {
            if (semester.getYearStudy().equals(yearStudy)) {
                semesterId = semester.getId();
                break;
            }
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(auth.getName());
        List<Object[]> activities = this.activityService.getActivitiesJoined(u.getId(), semesterId, yearStudy);
        if (!activities.isEmpty()) {
            return new ResponseEntity<>(activities, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/create", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createActivity(@RequestBody Map<String, String> data, @RequestPart MultipartFile file) throws ParseException {
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


//    @GetMapping(path = "/")
//    public List<ActivityResponse> getActivity() {
//        List<Activity> lists = this.activityService.getActivities();
//        return lists.stream().map(activity -> activityMapper.toActivityResponse(activity)).collect(Collectors.toList());
//    }

    
    @GetMapping(path="/{activityId}")
    public ActivityResponse getActivityById(@PathVariable("activityId") int activityId){
        Activity activity = this.activityService.findById(activityId);
        return this.activityMapper.toActivityResponse(activity);
    }


    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Object[]> getActivityDetail(@PathVariable int id) {
        Activity activity = this.activityService.findById(id);
        if (activity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        return new ResponseEntity<>(this.activityService.getActivity(id), HttpStatus.OK);
    }

    @PostMapping(path = "/update/{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateActivity(@PathVariable int id, @RequestBody Map<String, String> data, @RequestPart(required = false) MultipartFile file) throws ParseException {
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
    @CrossOrigin
    public ResponseEntity<Void> deleteActivity(@PathVariable int id) {
        boolean isDeleted = activityService.deleteActivity(id);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> listAll() {
        return new ResponseEntity<>(this.activityService.getAllActivities(), HttpStatus.OK);
    }
}
