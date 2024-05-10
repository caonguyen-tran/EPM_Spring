/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.Activity;
import com.epm.repositories.ActivityRepository;
import com.epm.services.ActivityService;
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
public class ActivityServiceImp implements ActivityService{
    @Autowired
    private ActivityRepository activityRepo;
    
    @Autowired 
    private Cloudinary cloudinary;
    @Override
    public List<Activity> getActivities() {
        return this.activityRepo.getActivities();
    }

    @Override
    public void createActivity(Activity activity) {
        if(!activity.getFile().isEmpty()){
            try {
                Map res = this.cloudinary.uploader().upload(activity.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                activity.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ActivityServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.activityRepo.createActivity(activity);
    }

    @Override
    public List<Activity> getActivitiesJoining(int accountStudentId) {
        return this.activityRepo.getActivitiesJoining(accountStudentId);
    }
    
}
