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

/**
 *
 * @author Win11
 */
@Entity
@Table(name = "result_join")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultJoin.findAll", query = "SELECT r FROM ResultJoin r"),
    @NamedQuery(name = "ResultJoin.findById", query = "SELECT r FROM ResultJoin r WHERE r.id = :id"),
    @NamedQuery(name = "ResultJoin.findByDateConfirm", query = "SELECT r FROM ResultJoin r WHERE r.dateConfirm = :dateConfirm"),
    @NamedQuery(name = "ResultJoin.findByNote", query = "SELECT r FROM ResultJoin r WHERE r.note = :note")})
public class ResultJoin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_confirm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConfirm;
    @Size(max = 255)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "join_activity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JoinActivity joinActivityId;

    public ResultJoin() {
    }

    public ResultJoin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateConfirm() {
        return dateConfirm;
    }

    public void setDateConfirm(Date dateConfirm) {
        this.dateConfirm = dateConfirm;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public JoinActivity getJoinActivityId() {
        return joinActivityId;
    }

    public void setJoinActivityId(JoinActivity joinActivityId) {
        this.joinActivityId = joinActivityId;
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
        if (!(object instanceof ResultJoin)) {
            return false;
        }
        ResultJoin other = (ResultJoin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.ResultJoin[ id=" + id + " ]";
    }
    
}
