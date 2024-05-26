/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.JoinActivity;
import com.epm.repositories.JoinActivityRepository;
import com.epm.services.JoinActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class JoinActivitityServiceImp implements JoinActivityService{
    @Autowired
    private JoinActivityRepository joinActivityRepository;
    
    
    @Override
    public List<JoinActivity> getParticipateByFaculty(int facultyId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getParticipates(String activityId, String facultyId, String classId, String semesterId) {
        return this.joinActivityRepository.getParticipates(activityId, facultyId, classId, semesterId);
    }

    @Override
    public List<JoinActivity> findByUserIdAndRollup(int userId, Boolean rollup) {
        return this.joinActivityRepository.findByUserIdAndRollup(userId, rollup);
    }

    @Override
    public void deleteJoinActivity(JoinActivity joinActivity) {
        this.joinActivityRepository.deleteJoinActivity(joinActivity);
    }
}
