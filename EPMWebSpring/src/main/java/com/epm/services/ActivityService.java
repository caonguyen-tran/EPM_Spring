/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.Activity;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface ActivityService {

    List<Activity> getActivities();

    void createActivity(Activity activity);
    
    List<Object[]> getActivitiesJoined(int userId, int semesterId, String yearStudy);
    
    List<Activity> getActivitiesMissingByUserId(int userId);
    
    List<Activity> findByTermId(int termId);
    
    Activity findById(int activityId);
    
    List<Activity> findBySemesterId(int semesterId);
}
