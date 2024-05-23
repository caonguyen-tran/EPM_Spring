/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Semester;
import com.epm.repositories.SemesterRepository;
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
public class SemesterRepositoryImp implements SemesterRepository{
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;
    
    @Override
    public List<Semester> getSemesters() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Semester.findAll");
        return query.getResultList();
    }

    @Override
    public List<Semester> getSemestersByStudyYear(String studyYear) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Semester.findByYearStudy");
        query.setParameter("yearStudy", studyYear);
        
        return query.getResultList();
    }

    @Override
    public Semester findById(int semesterId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Semester.findById");
        query.setParameter("id", semesterId);
        
        return (Semester) query.getSingleResult();
    }
}
