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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Win11
 */
@Entity
@Table(name = "assistant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assistant.findAll", query = "SELECT a FROM Assistant a"),
    @NamedQuery(name = "Assistant.findById", query = "SELECT a FROM Assistant a WHERE a.id = :id"),
    @NamedQuery(name = "Assistant.findByFirstname", query = "SELECT a FROM Assistant a WHERE a.firstname = :firstname"),
    @NamedQuery(name = "Assistant.findByLastname", query = "SELECT a FROM Assistant a WHERE a.lastname = :lastname"),
    @NamedQuery(name = "Assistant.findByGender", query = "SELECT a FROM Assistant a WHERE a.gender = :gender"),
    @NamedQuery(name = "Assistant.findByPhoneNumber", query = "SELECT a FROM Assistant a WHERE a.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Assistant.findByBirth", query = "SELECT a FROM Assistant a WHERE a.birth = :birth"),
    @NamedQuery(name = "Assistant.findByAddress", query = "SELECT a FROM Assistant a WHERE a.address = :address"),
    @NamedQuery(name = "Assistant.findByUsername", query = "SELECT a FROM Assistant a WHERE a.username = :username"),
    @NamedQuery(name = "Assistant.findByPassword", query = "SELECT a FROM Assistant a WHERE a.password = :password"),
    @NamedQuery(name = "Assistant.findByEmail", query = "SELECT a FROM Assistant a WHERE a.email = :email"),
    @NamedQuery(name = "Assistant.findByCreatedDate", query = "SELECT a FROM Assistant a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "Assistant.findByActive", query = "SELECT a FROM Assistant a WHERE a.active = :active"),
    @NamedQuery(name = "Assistant.findByAvatar", query = "SELECT a FROM Assistant a WHERE a.avatar = :avatar")})
public class Assistant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 3)
    @Column(name = "gender")
    private String gender;
    @Size(max = 10)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "active")
    private Boolean active;
    @Size(max = 100)
    @Column(name = "avatar")
    private String avatar;

    public Assistant() {
    }

    public Assistant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        if (!(object instanceof Assistant)) {
            return false;
        }
        Assistant other = (Assistant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.Assistant[ id=" + id + " ]";
    }
    
}
