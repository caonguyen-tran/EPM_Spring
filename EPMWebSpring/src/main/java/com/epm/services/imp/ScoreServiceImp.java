/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Term;
import com.epm.repositories.ActivityRepository;
import com.epm.repositories.ScoreRepository;
import com.epm.repositories.ScoreStudentRepository;
import com.epm.repositories.TermRepository;
import com.epm.services.ScoreService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MyLaptop
 */
@Service
public class ScoreServiceImp implements ScoreService{
    @Autowired
    private TermRepository termRepo;
    
    @Autowired
    private ActivityRepository activityRepo;
    
    @Autowired
    private ScoreRepository scoreRepo;
    
    @Autowired
    private ScoreStudentRepository scoreStudentRepo;

    @Override
    public Map<String, Integer> getTotalScoresByTerm(int accountStudentId) {
        Map<String, Integer> result = new HashMap<>();
        List<ScoreStudent> scoreStudents = scoreStudentRepo.findByAccountStudentId(accountStudentId);

        for (ScoreStudent scoreStudent : scoreStudents) {
            Score score = scoreRepo.findById(scoreStudent.getId());
            if (score != null) {
                Activity activity = activityRepo.findById(score.getActivityId().getId());
                if (activity != null) {
                    Term term = termRepo.findById(activity.getTermId().getId());
                    if (term != null) {
                        result.put(term.getName(), result.getOrDefault(term.getName(), 0) + score.getScoreValue());
                    }
                }
            }
        }
        
        return result;
    }

    @Override
    public int getTotalScores(int accountStudentId) {
                List<ScoreStudent> scoreStudents = scoreStudentRepo.findByAccountStudentId(accountStudentId);

        int totalScore = 0;
        for (ScoreStudent scoreStudent : scoreStudents) {
            Score score = scoreRepo.findById(scoreStudent.getScoreId().getId());
            if (score != null) {
                totalScore += score.getScoreValue();
            }
        }

        return totalScore;
    }
}

