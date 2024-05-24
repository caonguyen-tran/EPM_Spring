/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Score;
import com.epm.repositories.ScoreRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MyLaptop
 */
@Repository
@Transactional
public class ScoreRepositoryImp implements ScoreRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Score> findAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Score.findAll");
        
        return q.getResultList();
    }

    @Override
    public Score findById(int scoreId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Score.findById");
        q.setParameter("id", scoreId);
        
        return (Score) q.getSingleResult();
    }

    @Override
    public List<Score> findByActivityId(int activityId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Score.findByActivityId");
        q.setParameter("activityId", activityId);
        
        return q.getResultList();
    }
    
}
