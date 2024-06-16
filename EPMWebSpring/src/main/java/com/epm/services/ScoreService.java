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

    Map<String, Integer> getTotalScoresByTerm(int userId);

    int getTotalScores(int userId);

    List<Score> getScoresByUserIdAndSemesterId(int userId, int semesterId);

    List<Score> getScoresByStudentAndYear(int userId, String studyYear);

    List<Score> findAll();

    Score findById(int scoreId);

    List<Score> findByActivityId(int activityId);
    
    Score findByActivityWithScoreType(int activityId, String scoreType);
    
    List<Object[]> getScoresWithTerm(int userId, int semesterId, String yearStudy);
    
    List<Object[]> getTotalScoresByTerm(int userId, int semesterId, String yearStudy);
    
    Score getScoreByNameAndActivity(String scoreName, int activityId);
}
