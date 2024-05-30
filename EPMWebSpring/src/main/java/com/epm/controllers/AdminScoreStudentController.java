/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.services.JoinActivityService;
import com.epm.services.ScoreService;
import com.epm.services.ScoreStudentService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
public class AdminScoreStudentController {
    @Autowired
    private ScoreService scoreService;
    
    @Autowired
    private JoinActivityService joinActivityService;
    
    @Autowired
    private ScoreStudentService scoreStudentService;
    
    @PostMapping(value = "/join/accept")
    public String acceptScore(@RequestParam HashMap<String, String> params) {
        int joinId = Integer.parseInt(params.get("joinId"));
        int activityId = Integer.parseInt(params.get("activityId"));

        Score s = this.scoreService.findByActivityWithScoreType(activityId, "");
        System.out.println(s.getScoreValue());
        JoinActivity join = this.joinActivityService.getJoinActivityById(joinId);
        
        ScoreStudent scoreStudent = new ScoreStudent();
        scoreStudent.setJoinActivityId(join);
        scoreStudent.setScoreId(s);
        
        this.scoreStudentService.createScoreStudent(scoreStudent);
        return "join";
    }
}
