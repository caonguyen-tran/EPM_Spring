/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.Faculty;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.repositories.JoinActivityRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
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
public class JoinActivityRepositoryImp implements JoinActivityRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    //Get list register or participates by user id
    @Override
    public List<JoinActivity> findByUserIdAndRollup(int userId, Boolean rollup) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<JoinActivity> q = b.createQuery(JoinActivity.class);
        Root r = q.from(JoinActivity.class);
        q.select(r);

        q.where(b.and(b.equal(r.get("userId"), userId), b.equal(r.get("rollup"), rollup)));

        return s.createQuery(q).getResultList();
    }

    @Override
    public List<Object[]> getParticipates(String activityId, String facultyId, String classId, String semesterId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        List<Predicate> predicates = new ArrayList<>();

        Root<JoinActivity> join = criteriaQuery.from(JoinActivity.class);
        Join<JoinActivity, User> userStudent = join.join("userId", JoinType.INNER);
        Join<JoinActivity, Activity> activity = join.join("activityId", JoinType.INNER);
        Join<Activity, Semester> semester = activity.join("semesterId", JoinType.INNER);
        Join<User, Student> student = userStudent.join("student", JoinType.INNER);
        Join<Activity, Term> term = activity.join("termId", JoinType.INNER);
        Join<Student, Classes> classes = student.join("classId", JoinType.INNER);
        Join<Classes, Faculty> faculty = classes.join("facultyId", JoinType.INNER);

        criteriaQuery.select(criteriaBuilder.array(join, userStudent, activity, semester, student, term, classes, faculty));

        predicates.add(criteriaBuilder.equal(join.get("rollup"), true));
        predicates.add(criteriaBuilder.equal(join.get("accept"), false));
        if (activityId != null && !activityId.isEmpty()) {
            predicates.add(criteriaBuilder.equal(activity.get("id"), Integer.parseInt(activityId)));
        }

        if (facultyId != null && !facultyId.isEmpty()) {
            predicates.add(criteriaBuilder.equal(faculty.get("id"), Integer.parseInt(facultyId)));
        }
        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<JoinActivity> getParticipateByFaculty(int facultyId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteJoinActivity(JoinActivity joinActivity) {
        Session s = this.factory.getObject().getCurrentSession();
        s.remove(joinActivity);
    }

    @Override
    public void updateAccept(JoinActivity joinActivity) {
        Session s = this.factory.getObject().getCurrentSession();
        joinActivity.setAccept(true);
        s.update(joinActivity);
    }

    @Override
    public JoinActivity getJoinActivityById(int joinActivityId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("JoinActivity.findById");
        q.setParameter("id", joinActivityId);
        return (JoinActivity) q.getSingleResult();
    }

    @Override
    public JoinActivity findByUserAndActivity(int userId, int activityId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<JoinActivity> q = b.createQuery(JoinActivity.class);
        Root<JoinActivity> r = q.from(JoinActivity.class);

        q.select(r);

        Predicate userPredicate = b.equal(r.get("userId"), userId);
        Predicate activityPredicate = b.equal(r.get("activityId"), activityId);
        q.where(b.and(userPredicate, activityPredicate));

        try {
            return s.createQuery(q).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public JoinActivity update(JoinActivity joinActivity) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(joinActivity);
        return joinActivity;
    }

    @Override
    public List<JoinActivity> getJoinActivityByActivityId(int activityId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<JoinActivity> criteriaQuery = criteriaBuilder.createQuery(JoinActivity.class);
        Root root = criteriaQuery.from(JoinActivity.class);

        criteriaQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("rollup"), true));
        predicates.add(criteriaBuilder.equal(root.get("accept"), false));
        predicates.add(criteriaBuilder.equal(root.get("activityId"), activityId));

        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<JoinActivity> getJoinActivityByUserAndSemester(int semesterId, int userId, boolean accept) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<JoinActivity> criteriaQuery = criteriaBuilder.createQuery(JoinActivity.class);

        List<Predicate> predicates = new ArrayList<>();

        Root<JoinActivity> join = criteriaQuery.from(JoinActivity.class);
        Join<JoinActivity, Activity> activity = join.join("activityId", JoinType.INNER);
        Join<Activity, Semester> semester = activity.join("semesterId", JoinType.INNER);

        predicates.add(criteriaBuilder.equal(join.get("userId"), userId));
        predicates.add(criteriaBuilder.equal(join.get("accept"), accept));
        predicates.add(criteriaBuilder.equal(semester.get("id"), semesterId));

        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Object[] getAcByJAId(int joinActivityId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root r = q.from(JoinActivity.class);

        q.multiselect(r, r.get("activityId"));

        q.where(b.equal(r.get("id"), joinActivityId));

        return s.createQuery(q).getSingleResult();
    }
//
//    @Override
//    public List<JoinActivity> getJoinActivityByUser(int userId) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<JoinActivity> q = b.createQuery(JoinActivity.class);
//        Root r = q.from(JoinActivity.class);
//        q.select(r);
//
//        List<Predicate> predicates = new ArrayList();
//        predicates.add(b.equal(r.get("userId"), userId));
//        predicates.add(b.equal(r.get("rollup"), true));
//        predicates.add(b.equal(r.get("accept"), false));
//
//        q.where(predicates.toArray(Predicate[]::new));
//        Query query = s.createQuery(q);
//
//        try{
//            return query.getResultList();
//        }
//        catch(Exception ex){
//            return null;
//        }
//    }
}
