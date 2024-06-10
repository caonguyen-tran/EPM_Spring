/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.dto.response;

import com.epm.pojo.Activity;
import com.epm.pojo.User;
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
    private Activity activity;
    private User user;

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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
