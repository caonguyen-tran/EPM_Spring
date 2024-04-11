/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "like")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Like1.findAll", query = "SELECT l FROM Like1 l"),
    @NamedQuery(name = "Like1.findByActivityId", query = "SELECT l FROM Like1 l WHERE l.like1PK.activityId = :activityId"),
    @NamedQuery(name = "Like1.findByAccountStudentId", query = "SELECT l FROM Like1 l WHERE l.like1PK.accountStudentId = :accountStudentId"),
    @NamedQuery(name = "Like1.findByActive", query = "SELECT l FROM Like1 l WHERE l.active = :active")})
public class Like1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Like1PK like1PK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "account_student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AccountStudent accountStudent;
    @JoinColumn(name = "activity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Activity activity;

    public Like1() {
    }

    public Like1(Like1PK like1PK) {
        this.like1PK = like1PK;
    }

    public Like1(Like1PK like1PK, boolean active) {
        this.like1PK = like1PK;
        this.active = active;
    }

    public Like1(int activityId, int accountStudentId) {
        this.like1PK = new Like1PK(activityId, accountStudentId);
    }

    public Like1PK getLike1PK() {
        return like1PK;
    }

    public void setLike1PK(Like1PK like1PK) {
        this.like1PK = like1PK;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AccountStudent getAccountStudent() {
        return accountStudent;
    }

    public void setAccountStudent(AccountStudent accountStudent) {
        this.accountStudent = accountStudent;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (like1PK != null ? like1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Like1)) {
            return false;
        }
        Like1 other = (Like1) object;
        if ((this.like1PK == null && other.like1PK != null) || (this.like1PK != null && !this.like1PK.equals(other.like1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.Like1[ like1PK=" + like1PK + " ]";
    }
    
}
