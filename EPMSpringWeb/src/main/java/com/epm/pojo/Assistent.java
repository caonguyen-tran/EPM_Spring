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
 * @author ACER
 */
@Entity
@Table(name = "assistent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assistent.findAll", query = "SELECT a FROM Assistent a"),
    @NamedQuery(name = "Assistent.findById", query = "SELECT a FROM Assistent a WHERE a.id = :id"),
    @NamedQuery(name = "Assistent.findByFirstname", query = "SELECT a FROM Assistent a WHERE a.firstname = :firstname"),
    @NamedQuery(name = "Assistent.findByLastname", query = "SELECT a FROM Assistent a WHERE a.lastname = :lastname"),
    @NamedQuery(name = "Assistent.findByGender", query = "SELECT a FROM Assistent a WHERE a.gender = :gender"),
    @NamedQuery(name = "Assistent.findByPhoneNumber", query = "SELECT a FROM Assistent a WHERE a.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Assistent.findByAssistentcol", query = "SELECT a FROM Assistent a WHERE a.assistentcol = :assistentcol"),
    @NamedQuery(name = "Assistent.findByDayOfBirth", query = "SELECT a FROM Assistent a WHERE a.dayOfBirth = :dayOfBirth"),
    @NamedQuery(name = "Assistent.findByAddress", query = "SELECT a FROM Assistent a WHERE a.address = :address"),
    @NamedQuery(name = "Assistent.findByUsername", query = "SELECT a FROM Assistent a WHERE a.username = :username"),
    @NamedQuery(name = "Assistent.findByPassword", query = "SELECT a FROM Assistent a WHERE a.password = :password"),
    @NamedQuery(name = "Assistent.findByEmail", query = "SELECT a FROM Assistent a WHERE a.email = :email"),
    @NamedQuery(name = "Assistent.findByCreatedDate", query = "SELECT a FROM Assistent a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "Assistent.findByActive", query = "SELECT a FROM Assistent a WHERE a.active = :active"),
    @NamedQuery(name = "Assistent.findByAvatar", query = "SELECT a FROM Assistent a WHERE a.avatar = :avatar")})
public class Assistent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Size(max = 20)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 45)
    @Column(name = "assistentcol")
    private String assistentcol;
    @Column(name = "day_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dayOfBirth;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "active")
    private Boolean active;
    @Size(max = 80)
    @Column(name = "avatar")
    private String avatar;

    public Assistent() {
    }

    public Assistent(Integer id) {
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

    public String getAssistentcol() {
        return assistentcol;
    }

    public void setAssistentcol(String assistentcol) {
        this.assistentcol = assistentcol;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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
        if (!(object instanceof Assistent)) {
            return false;
        }
        Assistent other = (Assistent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epm.pojo.Assistent[ id=" + id + " ]";
    }
    
}
