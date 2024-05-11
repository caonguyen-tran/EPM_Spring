/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "join_activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JoinActivity.findAll", query = "SELECT j FROM JoinActivity j"),
    @NamedQuery(name = "JoinActivity.findById", query = "SELECT j FROM JoinActivity j WHERE j.id = :id"),
    @NamedQuery(name = "JoinActivity.findByDateRegister", query = "SELECT j FROM JoinActivity j WHERE j.dateRegister = :dateRegister"),
    @NamedQuery(name = "JoinActivity.findByRollup", query = "SELECT j FROM JoinActivity j WHERE j.rollup = :rollup"),
    @NamedQuery(name = "JoinActivity.findByProofJoining", query = "SELECT j FROM JoinActivity j WHERE j.proofJoining = :proofJoining"),
    @NamedQuery(name = "JoinActivity.findByNote", query = "SELECT j FROM JoinActivity j WHERE j.note = :note")})
public class JoinActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_register")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegister;
    @Column(name = "rollup")
    private Boolean rollup = false;
    @Size(max = 160)
    @Column(name = "proof_joining")
    private String proofJoining;
    @Size(max = 120)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "account_student_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountStudent accountStudentId;
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activityId;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "joinActivityId", fetch = FetchType.LAZY)
//    private ScoreStudent scoreStudent;

    public JoinActivity() {
    }

    public JoinActivity(Integer id) {
        this.id = id;
    }

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

//    public ScoreStudent getScoreStudent() {
//        return scoreStudent;
//    }
//
//    public void setScoreStudent(ScoreStudent scoreStudent) {
//        this.scoreStudent = scoreStudent;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JoinActivity)) {
            return false;
        }
        JoinActivity other = (JoinActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.JoinActivity[ id=" + id + " ]";
    }
    
}
