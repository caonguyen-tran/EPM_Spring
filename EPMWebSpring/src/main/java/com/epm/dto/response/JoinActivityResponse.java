/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.dto.response;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class JoinActivityResponse {
    private Integer id;
    private Date dateRegister;
    private Boolean rollup;
    private String proofJoining;
    private String note;
    private Boolean accept;
    private ActivityResponse activityResponse;
    private UserResponse userResponse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Boolean getRollup() {
        return rollup;
    }

    public void setRollup(Boolean rollup) {
        this.rollup = rollup;
    }

    public String getProofJoining() {
        return proofJoining;
    }

    public void setProofJoining(String proofJoining) {
        this.proofJoining = proofJoining;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public ActivityResponse getActivityResponse() {
        return activityResponse;
    }

    public void setActivityResponse(ActivityResponse activityResponse) {
        this.activityResponse = activityResponse;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
    
    
}
