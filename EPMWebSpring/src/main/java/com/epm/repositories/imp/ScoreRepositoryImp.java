/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Semester;
import com.epm.repositories.ScoreRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
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
public class ScoreRepositoryImp implements ScoreRepository {

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

    @Override
    public Score findByActivityIdWithScoreType(int activityId, String scoreType) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Score> criteriaQuery = criteriaBuilder.createQuery(Score.class);
        Root<Score> root = criteriaQuery.from(Score.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("activityId"), activityId));
        Query q = s.createQuery(criteriaQuery);

        return (Score) q.getSingleResult();
    }

    @Override
    public List<Object[]> getScoresWithTerm(int userId, int semesterId, String yearStudy) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<ScoreStudent> rootScoreStudent = cq.from(ScoreStudent.class);
        Join<ScoreStudent, Score> joinScore = rootScoreStudent.join("scoreId");
        Join<ScoreStudent, JoinActivity> joinActivity = rootScoreStudent.join("joinActivityId");
        Join<JoinActivity, Activity> joinActivityActivity = joinActivity.join("activityId");
        Join<Activity, Semester> joinActivitySemester = joinActivityActivity.join("semesterId");

        cq.multiselect(joinActivity, joinScore, joinActivityActivity.get("termId"));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(joinActivity.get("userId"), userId));
        predicates.add(cb.equal(joinActivity.get("rollup"), true));

        if (semesterId > 0) {
            predicates.add(cb.equal(joinActivitySemester.get("id"), semesterId));
        } else if (yearStudy != null && !yearStudy.isEmpty() && semesterId == 0) {
            Subquery<Integer> subquerySemesterIdByYearStudy = cq.subquery(Integer.class);
            Root<Semester> rootSemesterByYearStudy = subquerySemesterIdByYearStudy.from(Semester.class);
            subquerySemesterIdByYearStudy.select(rootSemesterByYearStudy.get("id"));
            subquerySemesterIdByYearStudy.where(cb.equal(rootSemesterByYearStudy.get("yearStudy"), yearStudy));
            predicates.add(cb.in(joinActivitySemester.get("id")).value(subquerySemesterIdByYearStudy));
        } else {

        }

        cq.where(predicates.toArray(new Predicate[0]));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public List<Object[]> getTotalScoresByTerm(int userId, int semesterId, String yearStudy) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<ScoreStudent> rootScoreStudent = cq.from(ScoreStudent.class);
        Join<ScoreStudent, Score> joinScore = rootScoreStudent.join("scoreId");
        Join<ScoreStudent, JoinActivity> joinActivity = rootScoreStudent.join("joinActivityId");
        Join<JoinActivity, Activity> joinActivityActivity = joinActivity.join("activityId");
        Join<Activity, Semester> joinActivitySemester = joinActivityActivity.join("semesterId");

        cq.multiselect(
                joinActivityActivity.get("termId"),
                cb.sum(joinScore.get("scoreValue"))
        );

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(joinActivity.get("userId"), userId));
        predicates.add(cb.equal(joinActivity.get("rollup"), true));

        if (semesterId > 0) {
            predicates.add(cb.equal(joinActivitySemester.get("id"), semesterId));
        } else if (yearStudy != null && !yearStudy.isEmpty() && semesterId == 0) {
            Subquery<Integer> subquerySemesterIdByYearStudy = cq.subquery(Integer.class);
            Root<Semester> rootSemesterByYearStudy = subquerySemesterIdByYearStudy.from(Semester.class);
            subquerySemesterIdByYearStudy.select(rootSemesterByYearStudy.get("id"));
            subquerySemesterIdByYearStudy.where(cb.equal(rootSemesterByYearStudy.get("yearStudy"), yearStudy));
            predicates.add(cb.in(joinActivitySemester.get("id")).value(subquerySemesterIdByYearStudy));
        } else {

        }

        cq.where(predicates.toArray(new Predicate[0]));

        cq.groupBy(joinActivityActivity.get("termId"));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public Score getScoreByNameAndActivity(String scoreName, int activityId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Score> cq = cb.createQuery(Score.class);
        Root<Score> root = cq.from(Score.class);
        
        Predicate scoreNamePredicate = cb.equal(root.get("scoreName"), scoreName);
        Predicate activityPredicate = cb.equal(root.get("activityId"), activityId);
        cq.select(root).where(cb.and(scoreNamePredicate, activityPredicate));
        
        try {
            return session.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
