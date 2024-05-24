/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.ScoreService;
import java.util.Map;
import jdk.internal.net.http.frame.Http2Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MyLaptop
 */
@RestController
@RequestMapping("/api/account_student_id")
public class ApiScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/{accountStudentId}/scores-by-term")
    public Map<String, Integer> getScoresByTerm(@PathVariable int accountStudentId) {
        return scoreService.getTotalScoresByTerm(accountStudentId);
    }

    @GetMapping("/{accountStudentId}/total-scores")
    public int getTotalScores(@PathVariable int accountStudentId) {
        return scoreService.getTotalScores(accountStudentId);
    }
}
