/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import java.util.List;
import com.epm.pojo.Activity;
/**
 *
 * @author ACER
 */
public interface ActivityRepository {
    List<Activity> getActivities();

    public void createActivity(Activity activity);
    
    List<Activity> getActivitiesJoining(int accountStudentId);
}
