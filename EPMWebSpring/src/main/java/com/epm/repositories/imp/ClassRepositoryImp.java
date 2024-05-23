/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Classes;
import com.epm.repositories.ClassRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Win11
 */
@Repository
@Transactional
public class ClassRepositoryImp implements ClassRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Classes findById(int classId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Classes.findById");
        q.setParameter("id", classId);
        return (Classes) q.getSingleResult();
    }
    
}
