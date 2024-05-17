/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.ScoreStudent;
import com.epm.repositories.ScoreStudentRepository;
import java.util.List;
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
public class ScoreStudentRepositoryImp implements ScoreStudentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ScoreStudent> findAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ScoreStudent.findAll");

        return q.getResultList();
    }

    @Override
    public ScoreStudent findById(int scoreStudentId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ScoreStudent.findById");
        q.setParameter("id", scoreStudentId);

        return (ScoreStudent) q.getSingleResult();
    }

    @Override
    public List<ScoreStudent> findByAccountStudentId(int accountStudentId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ScoreStudent.findByAccountStudentId");
        q.setParameter("accountStudentId", accountStudentId);

        return q.getResultList();
    }

}
