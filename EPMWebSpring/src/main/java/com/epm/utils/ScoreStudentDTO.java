/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

/**
 *
 * @author ACER
 */
public class ScoreStudentDTO {

    private int joinId;
    private int activityId;
    
    public ScoreStudentDTO(int joinId, int activityId){
        this.joinId = joinId;
        this.activityId = activityId;
    }

    public int getJoinId() {
        return joinId;
    }

    public void setJoinId(int joinId) {
        this.joinId = joinId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
