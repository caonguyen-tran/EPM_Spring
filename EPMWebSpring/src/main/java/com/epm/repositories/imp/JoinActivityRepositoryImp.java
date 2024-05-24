/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.JoinActivity;
import com.epm.repositories.JoinActivityRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class JoinActivityRepositoryImp implements JoinActivityRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<JoinActivity> findByAccountStudentIdAndRollup(int accountStudentId, Boolean rollup) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<JoinActivity> q = b.createQuery(JoinActivity.class);
        Root r = q.from(JoinActivity.class);
        q.select(r);
        
        q.where(b.and(b.equal(r.get("accountStudentId"), accountStudentId), b.equal(r.get("rollup"), rollup)));
        
        return s.createQuery(q).getResultList();
    }
    
}
