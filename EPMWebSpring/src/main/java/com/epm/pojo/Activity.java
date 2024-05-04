/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< HEAD
=======
import javax.persistence.FetchType;
>>>>>>> 8be9e7de6c94e9291fa6a4d3ff1645283d8d46a3
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@Entity
@Table(name = "activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findById", query = "SELECT a FROM Activity a WHERE a.id = :id"),
    @NamedQuery(name = "Activity.findByName", query = "SELECT a FROM Activity a WHERE a.name = :name"),
    @NamedQuery(name = "Activity.findByStartDate", query = "SELECT a FROM Activity a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "Activity.findByEndDate", query = "SELECT a FROM Activity a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "Activity.findByDescription", query = "SELECT a FROM Activity a WHERE a.description = :description"),
    @NamedQuery(name = "Activity.findByActive", query = "SELECT a FROM Activity a WHERE a.active = :active"),
    @NamedQuery(name = "Activity.findByImage", query = "SELECT a FROM Activity a WHERE a.image = :image"),
    @NamedQuery(name = "Activity.findBySlots", query = "SELECT a FROM Activity a WHERE a.slots = :slots"),
    @NamedQuery(name = "Activity.findByClose", query = "SELECT a FROM Activity a WHERE a.close = :close")})
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "start_date")
<<<<<<< HEAD
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
=======
    private Timestamp startDate;
    @Column(name = "end_date")
    private Timestamp endDate;
>>>>>>> 8be9e7de6c94e9291fa6a4d3ff1645283d8d46a3
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "active")
    private Boolean active = true;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "slots")
    private int slots;
    @Column(name = "close")
<<<<<<< HEAD
    private Boolean close;
=======
    private Boolean close = false;
    @JsonIgnore
>>>>>>> 8be9e7de6c94e9291fa6a4d3ff1645283d8d46a3
    @JoinColumn(name = "assistant_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Assistant assistantId;
<<<<<<< HEAD
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Faculty facultyId;
=======
    @JsonIgnore
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Faculty facultyId;
    @JsonIgnore
>>>>>>> 8be9e7de6c94e9291fa6a4d3ff1645283d8d46a3
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Semester semesterId;
    @JsonIgnore
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Term termId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Set<Liked> likedSet;
<<<<<<< HEAD
=======
    @JsonIgnore
>>>>>>> 8be9e7de6c94e9291fa6a4d3ff1645283d8d46a3
    @OneToMany(mappedBy = "activityId")
    private Set<JoinActivity> joinActivitySet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Set<Score> scoreSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Set<Comment> commentSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Set<MissingReport> missingReportSet;
    @Transient
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Activity() {
    }

    public Activity(Integer id) {
        this.id = id;
    }

    public Activity(Integer id, String image, int slots) {
        this.id = id;
        this.image = image;
        this.slots = slots;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    public Assistant getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Assistant assistantId) {
        this.assistantId = assistantId;
    }

    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }

    public Semester getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Semester semesterId) {
        this.semesterId = semesterId;
    }

    public Term getTermId() {
        return termId;
    }

    public void setTermId(Term termId) {
        this.termId = termId;
    }

    @XmlTransient
    public Set<Liked> getLikedSet() {
        return likedSet;
    }

    public void setLikedSet(Set<Liked> likedSet) {
        this.likedSet = likedSet;
    }

    @XmlTransient
    public Set<JoinActivity> getJoinActivitySet() {
        return joinActivitySet;
    }

    public void setJoinActivitySet(Set<JoinActivity> joinActivitySet) {
        this.joinActivitySet = joinActivitySet;
    }

    @XmlTransient
    public Set<Score> getScoreSet() {
        return scoreSet;
    }

    public void setScoreSet(Set<Score> scoreSet) {
        this.scoreSet = scoreSet;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @XmlTransient
    public Set<MissingReport> getMissingReportSet() {
        return missingReportSet;
    }

    public void setMissingReportSet(Set<MissingReport> missingReportSet) {
        this.missingReportSet = missingReportSet;
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
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.Activity[ id=" + id + " ]";
    }

}
