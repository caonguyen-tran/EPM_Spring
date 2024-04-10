/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "JoinActivity.findByJoinProof", query = "SELECT j FROM JoinActivity j WHERE j.joinProof = :joinProof"),
    @NamedQuery(name = "JoinActivity.findByActive", query = "SELECT j FROM JoinActivity j WHERE j.active = :active")})
public class JoinActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 125)
    @Column(name = "join_proof")
    private String joinProof;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "register_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Register registerId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "joinId")
    private ResultJoin resultJoin;

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

    public String getJoinProof() {
        return joinProof;
    }

    public void setJoinProof(String joinProof) {
        this.joinProof = joinProof;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Register getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Register registerId) {
        this.registerId = registerId;
    }

    public ResultJoin getResultJoin() {
        return resultJoin;
    }

    public void setResultJoin(ResultJoin resultJoin) {
        this.resultJoin = resultJoin;
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
