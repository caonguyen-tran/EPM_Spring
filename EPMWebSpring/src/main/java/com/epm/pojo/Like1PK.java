/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ACER
 */
@Embeddable
public class Like1PK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "activity_id")
    private int activityId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "account_student_id")
    private int accountStudentId;

    public Like1PK() {
    }

    public Like1PK(int activityId, int accountStudentId) {
        this.activityId = activityId;
        this.accountStudentId = accountStudentId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getAccountStudentId() {
        return accountStudentId;
    }

    public void setAccountStudentId(int accountStudentId) {
        this.accountStudentId = accountStudentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) activityId;
        hash += (int) accountStudentId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Like1PK)) {
            return false;
        }
        Like1PK other = (Like1PK) object;
        if (this.activityId != other.activityId) {
            return false;
        }
        if (this.accountStudentId != other.accountStudentId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.Like1PK[ activityId=" + activityId + ", accountStudentId=" + accountStudentId + " ]";
    }
    
}
