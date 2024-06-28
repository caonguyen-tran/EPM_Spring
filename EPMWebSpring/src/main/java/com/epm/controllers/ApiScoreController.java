/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Classes;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.User;
import com.epm.services.ClassService;
import com.epm.services.ScoreService;
import com.epm.services.SemesterService;
import com.epm.services.StudentService;
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
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

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
        Integer semesterId = null;
        Integer studentId = null;
        List<Object[]> totalScores;
        try {
            if (params.containsKey("semesterId") && params.get("semesterId") != null && !params.get("semesterId").isEmpty()) {
                semesterId = Integer.parseInt(params.get("semesterId"));
            }
            if (params.containsKey("studentId") && params.get("studentId") != null && !params.get("studentId").isEmpty()) {
                studentId = Integer.parseInt(params.get("studentId"));
            }

            Semester s = this.semesterService.findById(semesterId);
            String yearStudy = s.getYearStudy();
            User u = this.userService.findByStudentId(studentId);
            totalScores = this.scoreService.getTotalScoresByTerm(u.getId(), semesterId, yearStudy);

            if (totalScores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (NumberFormatException | NullPointerException e) {
            if (semesterId != null) {
                Semester s = this.semesterService.findById(semesterId);
                String yearStudy = s.getYearStudy();
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = this.userService.getUserByUsername(auth.getName());
                totalScores = this.scoreService.getTotalScoresByTerm(u.getId(), semesterId, yearStudy);
            } else if (studentId != null) {
                String yearStudy = params.get("yearStudy");
                User u = this.userService.findByStudentId(studentId);
                totalScores = this.scoreService.getTotalScoresByTerm(u.getId(), 0, yearStudy);
            } else {
                String yearStudy = params.get("yearStudy");
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = this.userService.getUserByUsername(auth.getName());
                totalScores = this.scoreService.getTotalScoresByTerm(u.getId(), 0, yearStudy);
            }

            if (totalScores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
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

    @GetMapping(path = "/average-score/classes", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Map<String, Object>>> getAverageScoreClass(@RequestParam Map<String, String> params) {
        Integer semesterId = null;
        List<Object[]> totalScores;
        try {
            semesterId = Integer.parseInt(params.get("semesterId")); // Potential NPE if params.get("semesterId") is null
            Semester s = this.semesterService.findById(semesterId); // Potential NPE if semesterService.findById returns null
            String yearStudy = s.getYearStudy(); // Potential NPE if s is null
            List<Classes> classes = this.classService.getClasses(); // Potential NPE if classService.getClasses returns null

            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Classes everyClass : classes) {
                List<Object[]> students = this.studentService.getListStudents(everyClass.getId()); // Potential NPE if studentService.getListStudents returns null

                if (students.isEmpty()) {
                    continue;
                }

                double totalScore = 0;
                int studentCount = students.size();

                for (Object[] studentEntry : students) {
                    Student student = (Student) studentEntry[0];
                    int userId = (Integer) studentEntry[1];

                    totalScores = this.scoreService.getTotalScoresByTerm(userId, semesterId, yearStudy);

                    if (!totalScores.isEmpty()) {
                        for (Object[] result : totalScores) {
                            totalScore += ((Number) result[1]).doubleValue();
                        }
                    }
                }

                double averageScore = totalScore / studentCount;

                Map<String, Object> response = new HashMap<>();
                response.put("classId", everyClass.getId());
                response.put("className", everyClass.getName());
                response.put("averageScore", averageScore);
                response.put("studentCount", studentCount);

                responseList.add(response);
            }

            if (responseList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(responseList, HttpStatus.OK);

        } catch (NumberFormatException | NullPointerException e) {
            String yearStudy = params.get("yearStudy");
            semesterId = 0;
            List<Classes> classes = this.classService.getClasses();

            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Classes everyClass : classes) {
                List<Object[]> students = this.studentService.getListStudents(everyClass.getId());

                if (students.isEmpty()) {
                    continue;
                }

                double totalScore = 0;
                int studentCount = students.size();

                for (Object[] studentEntry : students) {
                    Student student = (Student) studentEntry[0];
                    if (studentEntry.length < 2 || studentEntry[1] == null) {
                        continue; // Skip this studentEntry if userId is null
                    }
                    Integer userId = (Integer) studentEntry[1];

                    totalScores = this.scoreService.getTotalScoresByTerm(userId, semesterId, yearStudy);

                    if (!totalScores.isEmpty()) {
                        for (Object[] result : totalScores) {
                            totalScore += ((Number) result[1]).doubleValue(); 
                        }
                    }
                }

                double averageScore = totalScore / studentCount;

                Map<String, Object> response = new HashMap<>();
                response.put("classId", everyClass.getId());
                response.put("className", everyClass.getName());
                response.put("averageScore", averageScore);
                response.put("studentCount", studentCount);

                responseList.add(response);
            }

            if (responseList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
    }

}
