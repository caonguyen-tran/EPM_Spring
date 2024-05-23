/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.Score;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Win11
 */
public interface ScoreService {

    Map<String, Integer> getTotalScoresByTerm(int accountStudentId);

    int getTotalScores(int accountStudentId);

    List<Score> getScoresByAccountStudentIdAndSemester(int accountStudentId, int semesterId);
    
    List<Score> getScoresByStudentAndYear(int accountStudentId, String studyYear);
}


