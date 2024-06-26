/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.ActivityResponse;
import com.epm.pojo.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class ActivityMapper {
    @Autowired
    private UserMapper userMapper;
    
    public ActivityResponse toActivityResponse(Activity activity){
        ActivityResponse ar = new ActivityResponse();
        
        ar.setId(activity.getId());
        ar.setName(activity.getName());
        ar.setStartDate(activity.getStartDate());
        ar.setEndDate(activity.getEndDate());
        ar.setDescription(activity.getDescription());
        ar.setImage(activity.getImage());
        ar.setSlots(activity.getSlots());
        ar.setActive(activity.getActive());
        ar.setClose(activity.getClose());
        ar.setFaculty(activity.getFacultyId());
        ar.setSemester(activity.getSemesterId());
        ar.setTerm(activity.getTermId());
        ar.setUseResponse(userMapper.toUserResponse(activity.getCreatedUserId()));
        return ar;
    }
}
