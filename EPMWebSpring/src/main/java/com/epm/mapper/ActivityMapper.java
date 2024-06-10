/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.ActivityResponse;
import com.epm.pojo.Activity;

/**
 *
 * @author ACER
 */
public class ActivityMapper {
    public ActivityResponse toActivityResponse(Activity activity){
        ActivityResponse ar = new ActivityResponse();
        
        ar.setId(activity.getId());
        ar.setName(activity.getName());
        ar.setStartDate(activity.getStartDate());
        ar.setEndDate(activity.getEndDate());
        ar.setDescription(activity.getDescription());
        ar.setImage(activity.getImage());
        ar.setActive(activity.getActive());
        ar.setFaculty(activity.getFacultyId());
        ar.setSemester(activity.getSemesterId());
        ar.setTerm(activity.getTermId());
        return ar;
    }
}