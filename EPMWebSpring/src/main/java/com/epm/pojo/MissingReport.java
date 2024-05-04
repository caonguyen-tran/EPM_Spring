/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Win11
 */
@Entity
@Table(name = "missing_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MissingReport.findAll", query = "SELECT m FROM MissingReport m"),
    @NamedQuery(name = "MissingReport.findById", query = "SELECT m FROM MissingReport m WHERE m.id = :id"),
    @NamedQuery(name = "MissingReport.findByProofJoining", query = "SELECT m FROM MissingReport m WHERE m.proofJoining = :proofJoining"),
    @NamedQuery(name = "MissingReport.findByStatus", query = "SELECT m FROM MissingReport m WHERE m.status = :status"),
    @NamedQuery(name = "MissingReport.findByCreatedDate", query = "SELECT m FROM MissingReport m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "MissingReport.findByNote", query = "SELECT m FROM MissingReport m WHERE m.note = :note")})
public class MissingReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "proof_joining")
    private String proofJoining;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 120)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "account_student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AccountStudent accountStudentId;
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Activity activityId;

    public MissingReport() {
    }

    public MissingReport(Integer id) {
        this.id = id;
    }

    public MissingReport(Integer id, String proofJoining) {
        this.id = id;
        this.proofJoining = proofJoining;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProofJoining() {
        return proofJoining;
    }

    public void setProofJoining(String proofJoining) {
        this.proofJoining = proofJoining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MissingReport)) {
            return false;
        }
        MissingReport other = (MissingReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.MissingReport[ id=" + id + " ]";
    }
    
}
