/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.ScoreStudentResponse;
import com.epm.mapper.ScoreStudentMapper;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.services.JoinActivityService;
import com.epm.services.ScoreService;
import com.epm.services.ScoreStudentService;
import com.epm.utils.ScoreStudentDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RestController;

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
    private ScoreStudentMapper scoreStudentMapper;
    
    @GetMapping("/account_student_id/{studentId}/scores/semester/{semesterId}")
    public ResponseEntity<List<Score>> getScoresBySemester(@PathVariable int studentId, @PathVariable int semesterId) {
        List<Score> scores = scoreService.getScoresByUserIdAndSemesterId(studentId, semesterId);
        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/account_student_id/{studentId}/scores/year/{studyYear}")
    public ResponseEntity<List<Score>> getScoresByYear(@PathVariable int studentId, @PathVariable String studyYear) {
        List<Score> scores = scoreService.getScoresByStudentAndYear(studentId, studyYear);
        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }
    
    @PostMapping(path = "/score-student/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreStudent> createScoreStudent(@RequestBody Map<String, Integer> payload){
        ScoreStudent scoreStudent = new ScoreStudent();
        JoinActivity joinActivity = this.joinActivityService.getJoinActivityById(payload.get("joinId"));
        Score score = this.scoreService.findByActivityWithScoreType(payload.get("activityId"), "");
        
        scoreStudent.setJoinActivityId(joinActivity);
        scoreStudent.setScoreId(score);
        
        ScoreStudent results = this.scoreStudentService.createScoreStudent(scoreStudent);
        this.joinActivityService.updateAccept(joinActivity);
        return new ResponseEntity<>(results, HttpStatus.CREATED);
    }
    
//    @PostMapping(path="/score-student/accept-all/{activityId}")
//    public int createMultipleScoreStudent(@PathVariable("activityId") int activityId){
//        List<JoinActivity> lists = this.joinActivityService.getJoinActivityByActivityId(activityId);
//        
//    }
    
    @GetMapping(path="/score-student/scores/{userId}")
    public List<ScoreStudentResponse> getScoresOfUser(@PathVariable("userId") int userId){
        List<ScoreStudent> listScoreStudents = this.scoreStudentService.findByUserId(userId);
        return listScoreStudents.stream().map(scoreStudentMapper::toScoreStudentResponse).collect(Collectors.toList());
    }
}
