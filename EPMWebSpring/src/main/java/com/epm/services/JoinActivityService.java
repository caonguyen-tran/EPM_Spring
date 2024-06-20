/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.JoinActivity;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface JoinActivityService {

    List<JoinActivity> findByUserIdAndRollup(int userId, Boolean rollup);

    List<Object[]> getParticipates(String activityId, String facultyId, String classId, String semesterId);

    List<JoinActivity> getParticipateByFaculty(int facultyId);
    
    void deleteJoinActivity(JoinActivity joinActivity);
    
    void updateAccept(JoinActivity joinActivity);
    
    JoinActivity getJoinActivityById(int joinActivityId);
    
    JoinActivity findByUserAndActivity(int userId, int activityId);
    
    void update(JoinActivity joinActivity);

    public List<JoinActivity> getJoinActivityByActivityId(int activityId);
    
    List<JoinActivity> getJoinActivityByUserAndSemester(int semesterId, int userId);
}
