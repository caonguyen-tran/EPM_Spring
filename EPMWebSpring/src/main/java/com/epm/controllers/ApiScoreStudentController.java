/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.ResponseStruct;
import com.epm.mapper.ScoreStudentMapper;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.User;
import com.epm.services.JoinActivityService;
import com.epm.services.ScoreService;
import com.epm.services.ScoreStudentService;
import com.epm.services.UserService;
import com.epm.utils.StatusResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiScoreStudentController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreStudentService scoreStudentService;

    @Autowired
    private JoinActivityService joinActivityService;
    
    @Autowired
    private UserService userService;
    

    @PostMapping(path = "/score-student/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreStudent> createScoreStudent(@RequestBody Map<String, Integer> payload) {
        ScoreStudent scoreStudent = new ScoreStudent();
        JoinActivity joinActivity = this.joinActivityService.getJoinActivityById(payload.get("joinId"));
        Score score = this.scoreService.findByActivityWithScoreType(payload.get("activityId"), "");

        scoreStudent.setJoinActivityId(joinActivity);
        scoreStudent.setScoreId(score);

        ScoreStudent results = this.scoreStudentService.createScoreStudent(scoreStudent);
        this.joinActivityService.updateAccept(joinActivity);
        return new ResponseEntity<>(results, HttpStatus.CREATED);
    }

    @PostMapping("/score-student/upload-csv")
    public ResponseEntity<String> uploadCsv(@RequestPart MultipartFile file, @RequestParam("activityId") int activityId) {
        try {
            scoreStudentService.loadCsv(file, activityId);
            return ResponseEntity.ok("File processed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        }
    }
    
//    @PostMapping(path="/score-student/accept-all/{activityId}")
//    public int createMultipleScoreStudent(@PathVariable("activityId") int activityId){
//        List<JoinActivity> lists = this.joinActivityService.getJoinActivityByActivityId(activityId);
//        
//    }
//    
//    @GetMapping(path = "/score-student/scores/{userId}")
//    public ResponseEntity<String> getScoresOfUser(@PathVariable("userId") int userId){
//        List<ScoreStudent> listScoreStudents = this.scoreStudentService.findByUserId(userId);
//        return (ResponseEntity<String>) listScoreStudents.stream().map(scoreStudentMapper::toScoreStudentResponse).collect(Collectors.toList());
//    }
    
    @GetMapping(path="/score-student/result/{semesterId}")
    public ResponseStruct<List<Object[]>> getResult(@PathVariable(value="semesterId") int semesterId, Principal principal){
        User user = this.userService.getUserByUsername(principal.getName());
        
        return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, this.scoreStudentService.getScoreStudentByUserAndSemester(this.joinActivityService.getJoinActivityByUserAndSemester(semesterId, user.getId(), true)));
    }
    
    @GetMapping(path="/score-student/detail/{joinActivityId}")
    public ResponseEntity<List<Object[]>> getScoreByJA(@PathVariable("joinActivityId") int joinActivityId){
        List<Object[]> listScore = this.scoreStudentService.getScoreByJoinActivity(joinActivityId);
        if (listScore != null && !listScore.isEmpty())
            return new ResponseEntity<>(listScore, HttpStatus.OK);
        return null;
    }
}
