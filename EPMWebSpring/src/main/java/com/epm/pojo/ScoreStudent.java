/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Win11
 */
@Entity
@Table(name = "score_student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreStudent.findAll", query = "SELECT s FROM ScoreStudent s"),
    @NamedQuery(name = "ScoreStudent.findById", query = "SELECT s FROM ScoreStudent s WHERE s.id = :id"),
})
public class ScoreStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "join_activity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private JoinActivity joinActivityId;
    @JoinColumn(name = "score_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Score scoreId;

    public ScoreStudent() {
    }

    public ScoreStudent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JoinActivity getJoinActivityId() {
        return joinActivityId;
    }

    public void setJoinActivityId(JoinActivity joinActivityId) {
        this.joinActivityId = joinActivityId;
    }

    public Score getScoreId() {
        return scoreId;
    }

    public void setScoreId(Score scoreId) {
        this.scoreId = scoreId;
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
        if (!(object instanceof ScoreStudent)) {
            return false;
        }
        ScoreStudent other = (ScoreStudent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.ScoreStudent[ id=" + id + " ]";
    }
    
}
