/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.ScoreStudent;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface ScoreStudentService {

    List<ScoreStudent> findAll();

    ScoreStudent findById(int scoreStudentId);

    List<ScoreStudent> findByUserId(int userId);

    List<ScoreStudent> findByJoinActivityId(int joinActivityId);

    ScoreStudent createScoreStudent(ScoreStudent scoreStudent);

    int createMultipleScoreStudent(List<JoinActivity> listJoinActivities, int scoreId);
}
