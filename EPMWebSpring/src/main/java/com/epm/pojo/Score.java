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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
    @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id"),
    @NamedQuery(name = "Score.findByScoreName", query = "SELECT s FROM Score s WHERE s.scoreName = :scoreName"),
    @NamedQuery(name = "Score.findByScoreValue", query = "SELECT s FROM Score s WHERE s.scoreValue = :scoreValue"),
    @NamedQuery(name = "Score.findByDescription", query = "SELECT s FROM Score s WHERE s.description = :description"),
    @NamedQuery(name = "Score.findByNumberOfScore", query = "SELECT s FROM Score s WHERE s.numberOfScore = :numberOfScore")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "score_name")
    private String scoreName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score_value")
    private int scoreValue;
    @Size(max = 80)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_score")
    private int numberOfScore;
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Activity activityId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scoreId")
    private Set<ScoreStudent> scoreStudentSet;

    public Score() {
    }

    public Score(Integer id) {
        this.id = id;
    }

    public Score(Integer id, int scoreValue, int numberOfScore) {
        this.id = id;
        this.scoreValue = scoreValue;
        this.numberOfScore = numberOfScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfScore() {
        return numberOfScore;
    }

    public void setNumberOfScore(int numberOfScore) {
        this.numberOfScore = numberOfScore;
    }

    public Activity getActivityId() {
        return activityId;
    }

    public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

    @XmlTransient
    public Set<ScoreStudent> getScoreStudentSet() {
        return scoreStudentSet;
    }

    public void setScoreStudentSet(Set<ScoreStudent> scoreStudentSet) {
        this.scoreStudentSet = scoreStudentSet;
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
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.Score[ id=" + id + " ]";
    }
    
}
