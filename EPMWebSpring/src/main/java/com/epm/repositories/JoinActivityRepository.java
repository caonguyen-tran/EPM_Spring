/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.JoinActivity;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface JoinActivityRepository {

    List<JoinActivity> findByUserIdAndRollup(int userId, Boolean rollup);

    List<Object[]> getParticipates(String activityId, String facultyId, String classId, String semesterId);

    List<JoinActivity> getParticipateByFaculty(int facultyId);

    public void deleteJoinActivity(JoinActivity joinActivity);

    public void updateAccept(JoinActivity joinActivity);

    public JoinActivity getJoinActivityById(int joinActivityId);
}
