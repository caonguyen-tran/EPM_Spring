/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.ScoreStudent;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface ScoreStudentRepository {

    List<ScoreStudent> findAll();

    ScoreStudent findById(int scoreStudentId);

    List<ScoreStudent> findByUserId(int userId);
    
    List<ScoreStudent> findByJoinActivityId(int joinActivityId);   
    
    ScoreStudent createScoreStudent(ScoreStudent scoreStudent);
    
    void save(ScoreStudent scoreStudent);

    public int createMultipleScoreStudent(List<JoinActivity> listJoinActivities, int scoreId);

    public List<Object[]> getScoreStudentByUserAndSemester(List<JoinActivity> listJoinActivity);
}
