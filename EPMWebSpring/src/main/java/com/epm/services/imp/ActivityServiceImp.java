/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Activity;
import com.epm.repositories.ActivityRepository;
import com.epm.services.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ActivityServiceImp implements ActivityService{
    @Autowired
    private ActivityRepository activityRepo;
    
    @Override
    public List<Activity> getActivities() {
        return this.activityRepo.getActivities();
    }
    
}
