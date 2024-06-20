/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.ScoreStudent;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

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
    
    void save(ScoreStudent scoreStudent);
    
    void loadCsv(MultipartFile file, int activityId) throws IOException;

    int createMultipleScoreStudent(List<JoinActivity> listJoinActivities, int scoreId);
    
    List<Object[]> getScoreStudentByUserAndSemester(List<JoinActivity> listJoinActivity);
}
