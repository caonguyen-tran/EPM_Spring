/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Win11
 */
@Entity
@Table(name = "join_activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JoinActivity.findAll", query = "SELECT j FROM JoinActivity j"),
    @NamedQuery(name = "JoinActivity.findById", query = "SELECT j FROM JoinActivity j WHERE j.id = :id"),
    @NamedQuery(name = "JoinActivity.findByJoinProof", query = "SELECT j FROM JoinActivity j WHERE j.joinProof = :joinProof"),
    @NamedQuery(name = "JoinActivity.findByType", query = "SELECT j FROM JoinActivity j WHERE j.type = :type")})
public class JoinActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "join_proof")
    private String joinProof;
    @Column(name = "type")
    private Boolean type;
    @JoinColumn(name = "register_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Register registerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "joinActivityId")
    private Set<ResultJoin> resultJoinSet;

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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Register getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Register registerId) {
        this.registerId = registerId;
    }

    @XmlTransient
    public Set<ResultJoin> getResultJoinSet() {
        return resultJoinSet;
    }

    public void setResultJoinSet(Set<ResultJoin> resultJoinSet) {
        this.resultJoinSet = resultJoinSet;
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
