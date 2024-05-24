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
public interface JoinService {
    public List<Object[]> getParticipates(String activityId,String facultyId,String classId,String semesterId);
    public List<JoinActivity> getParticipateByFaculty(int facultyId);
}
