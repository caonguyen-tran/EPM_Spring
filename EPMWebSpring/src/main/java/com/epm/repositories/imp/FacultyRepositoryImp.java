/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Faculty;
import com.epm.repositories.FacultyRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
public class FacultyRepositoryImp implements FacultyRepository{
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;
    
    @Override
    public List<Faculty> getFaculties() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Faculty.findAll");
        return query.getResultList();
    }

    @Override
    public Faculty findById(int facultyId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Faculty.findById");
        query.setParameter("id", facultyId);
        return (Faculty) query.getSingleResult();
    }
}
