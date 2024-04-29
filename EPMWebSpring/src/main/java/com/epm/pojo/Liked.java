/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "liked")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Liked.findAll", query = "SELECT l FROM Liked l"),
    @NamedQuery(name = "Liked.findById", query = "SELECT l FROM Liked l WHERE l.id = :id"),
    @NamedQuery(name = "Liked.findByActive", query = "SELECT l FROM Liked l WHERE l.active = :active")})
public class Liked implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "account_student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AccountStudent accountStudentId;
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Activity activityId;

    public Liked() {
    }

    public Liked(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AccountStudent getAccountStudentId() {
        return accountStudentId;
    }

    public void setAccountStudentId(AccountStudent accountStudentId) {
        this.accountStudentId = accountStudentId;
    }

    public Activity getActivityId() {
        return activityId;
    }

    public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Liked)) {
            return false;
        }
        Liked other = (Liked) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.Liked[ id=" + id + " ]";
    }
    
}
