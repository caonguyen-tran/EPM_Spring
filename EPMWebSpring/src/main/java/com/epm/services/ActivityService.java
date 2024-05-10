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
    public void createActivity(Activity activity);
    public List<Activity> getActivitiesJoining(int accountStudentId);
}
