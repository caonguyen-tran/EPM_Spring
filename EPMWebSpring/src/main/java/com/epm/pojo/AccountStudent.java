/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@Entity
@Table(name = "account_student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountStudent.findAll", query = "SELECT a FROM AccountStudent a"),
    @NamedQuery(name = "AccountStudent.findById", query = "SELECT a FROM AccountStudent a WHERE a.id = :id"),
    @NamedQuery(name = "AccountStudent.findByUsername", query = "SELECT a FROM AccountStudent a WHERE a.username = :username"),
    @NamedQuery(name = "AccountStudent.findByPassword", query = "SELECT a FROM AccountStudent a WHERE a.password = :password"),
    @NamedQuery(name = "AccountStudent.findByAvatar", query = "SELECT a FROM AccountStudent a WHERE a.avatar = :avatar")})
public class AccountStudent implements Serializable, CustomUserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "avatar")
    private String avatar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountStudentId")
    @JsonIgnore
    private Set<Liked> likedSet;
    @OneToMany(mappedBy = "accountStudentId")
    @JsonIgnore
    private Set<JoinActivity> joinActivitySet;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Student studentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountStudentId")
    @JsonIgnore
    private Set<Comment> commentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountStudentId")
    @JsonIgnore
    private Set<MissingReport> missingReportSet;
    @Transient
    private MultipartFile file;

    public AccountStudent() {
    }

    public AccountStudent(Integer id) {
        this.id = id;
    }

    public AccountStudent(Integer id, String username, String password, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public AccountStudent(String username, String password, ArrayList<Object> arrayList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof AccountStudent)) {
            return false;
        }
        AccountStudent other = (AccountStudent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.AccountStudent[ id=" + id + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String getUserId() {
        return String.valueOf(this.id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No roles
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
