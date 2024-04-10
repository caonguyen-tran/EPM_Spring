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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "result_join")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultJoin.findAll", query = "SELECT r FROM ResultJoin r"),
    @NamedQuery(name = "ResultJoin.findById", query = "SELECT r FROM ResultJoin r WHERE r.id = :id"),
    @NamedQuery(name = "ResultJoin.findByConfirmDate", query = "SELECT r FROM ResultJoin r WHERE r.confirmDate = :confirmDate"),
    @NamedQuery(name = "ResultJoin.findByNote", query = "SELECT r FROM ResultJoin r WHERE r.note = :note")})
public class ResultJoin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "confirm_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmDate;
    @Size(max = 255)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "join_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private JoinActivity joinId;

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

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public JoinActivity getJoinId() {
        return joinId;
    }

    public void setJoinId(JoinActivity joinId) {
        this.joinId = joinId;
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
