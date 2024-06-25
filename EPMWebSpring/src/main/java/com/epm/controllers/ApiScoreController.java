/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Semester;
import com.epm.pojo.User;
import com.epm.services.ScoreService;
import com.epm.services.SemesterService;
import com.epm.services.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MyLaptop
 */
@RestController
@RequestMapping("/api/score")
public class ApiScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;

    @Autowired
    private SemesterService semesterService;

    @GetMapping(path = "/scores-by-term", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> getScoresByTerm(@RequestParam Map<String, String> params) {
        List<Semester> s = this.semesterService.findBySemesterName(params.get("semester"));
        String yearStudy = params.get("yearStudy");
        int semesterId = 0;
        for (Semester semester : s) {
            if (semester.getYearStudy().equals(yearStudy)) {
                semesterId = semester.getId();
                break;
            }
        }

        Integer studentId = null;

        try {
            studentId = Integer.parseInt(params.get("studentId"));
        } catch (NumberFormatException | NullPointerException e) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = this.userService.getUserByUsername(auth.getName());
            List<Object[]> scores = this.scoreService.getScoresWithTerm(u.getId(), semesterId, yearStudy);
            if (!scores.isEmpty()) {
                return new ResponseEntity<>(scores, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        User u = this.userService.findByStudentId(studentId);
        List<Object[]> scores = this.scoreService.getScoresWithTerm(u.getId(), semesterId, yearStudy);
        if (!scores.isEmpty()) {
            return new ResponseEntity<>(scores, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/total-scores-by-term", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Map<String, Object>> getTotalScoresByTerm(@RequestParam Map<String, String> params) {
        List<Semester> s = this.semesterService.findBySemesterName(params.get("semester"));
        String yearStudy = params.get("yearStudy");
        int semesterId = 0;
        for (Semester semester : s) {
            if (semester.getYearStudy().equals(yearStudy)) {
                semesterId = semester.getId();
                break;
            }
        }

        Integer studentId = null;

        try {
            studentId = Integer.parseInt(params.get("studentId"));
        } catch (NumberFormatException | NullPointerException e) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = this.userService.getUserByUsername(auth.getName());
            List<Object[]> totalScores = this.scoreService.getTotalScoresByTerm(u.getId(), semesterId, yearStudy);
            if (totalScores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new HashMap<>();
            List<Map<String, Object>> termScores = new ArrayList<>();
            double overallTotalScore = 0;

            for (Object[] result : totalScores) {
                Map<String, Object> termScore = new HashMap<>();
                termScore.put("termId", result[0]);
                termScore.put("totalScore", result[1]);
                termScores.add(termScore);

                overallTotalScore += ((Number) result[1]).doubleValue();
            }

            response.put("termScores", termScores);
            response.put("overallTotalScore", overallTotalScore);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        User u = this.userService.findByStudentId(studentId);
        List<Object[]> totalScores = this.scoreService.getTotalScoresByTerm(u.getId(), semesterId, yearStudy);
        if (totalScores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> termScores = new ArrayList<>();
        double overallTotalScore = 0;

        for (Object[] result : totalScores) {
            Map<String, Object> termScore = new HashMap<>();
            termScore.put("termId", result[0]);
            termScore.put("totalScore", result[1]);
            termScores.add(termScore);

            overallTotalScore += ((Number) result[1]).doubleValue();
        }

        response.put("termScores", termScores);
        response.put("overallTotalScore", overallTotalScore);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
