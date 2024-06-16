/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.ScoreStudent;
import com.epm.repositories.ScoreStudentRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
    public List<ScoreStudent> findByJoinActivityId(int joinActivityId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ScoreStudent> q = b.createQuery(ScoreStudent.class);
        Root r = q.from(ScoreStudent.class);
        q.select(r);

        q.where(b.equal(r.get("joinActivityId"), joinActivityId));

        return s.createQuery(q).getResultList();
    }

    @Override
    public List<ScoreStudent> findByUserId(int userId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ScoreStudent> q = b.createQuery(ScoreStudent.class);
        Root r = q.from(ScoreStudent.class);

        Join<ScoreStudent, JoinActivity> scoreJA = r.join("joinActivityId");

        q.where(b.equal(scoreJA.get("userId"), userId));

        return s.createQuery(q).getResultList();
    }

    @Override
    public ScoreStudent createScoreStudent(ScoreStudent scoreStudent) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(scoreStudent);
        return scoreStudent;
    }

    @Override
    public void save(ScoreStudent scoreStudent) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(scoreStudent);
    }
}
