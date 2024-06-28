/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.JoinActivity;
import com.epm.repositories.JoinActivityRepository;
import com.epm.services.JoinActivityService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Autowired
    private Cloudinary cloudinary;
    
    
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

    @Override
    public void updateAccept(JoinActivity joinActivity) {
        this.joinActivityRepository.updateAccept(joinActivity);
    }

    @Override
    public JoinActivity getJoinActivityById(int joinActivityId) {
        return this.joinActivityRepository.getJoinActivityById(joinActivityId);
    }

    @Override
    public JoinActivity findByUserAndActivity(int userId, int activityId) {
        return this.joinActivityRepository.findByUserAndActivity(userId, activityId);
    }
    
    @Override
    public JoinActivity update(JoinActivity joinActivity){
        if (!joinActivity.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(joinActivity.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                joinActivity.setProofJoining(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ActivityServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.joinActivityRepository.update(joinActivity);
    }
  
    @Override
    public List<JoinActivity> getJoinActivityByActivityId(int activityId) {
        return this.joinActivityRepository.getJoinActivityByActivityId(activityId);
    }

    @Override
    public List<JoinActivity> getJoinActivityByUserAndSemester(int semesterId, int userId, boolean accept) {
        return this.joinActivityRepository.getJoinActivityByUserAndSemester(semesterId, userId, accept);
    }

    @Override
    public Object[] getAcByJAId(int joinActivityId) {
        return this.joinActivityRepository.getAcByJAId(joinActivityId);
    }
}
