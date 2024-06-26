/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.Faculty;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.repositories.RegisterRepository;
import java.util.ArrayList;
import java.util.List;
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
 * @author ACER
 */
@Repository
@Transactional
public class RegisterRepositoryImp implements RegisterRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public List<Object[]> getRegisters() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<JoinActivity> register = criteriaQuery.from(JoinActivity.class);
        Join<JoinActivity, User> userStudent = register.join("userId", JoinType.INNER);
        Join<JoinActivity, Activity> activity = register.join("activityId", JoinType.INNER);
        Join<User, Student> student = userStudent.join("student", JoinType.INNER);
        Join<Activity, Term> term = activity.join("termId", JoinType.INNER);
        Join<Student, Classes> classes = student.join("classId", JoinType.INNER);
        Join<Classes, Faculty> faculty = classes.join("facultyId", JoinType.INNER);

        criteriaQuery.select(criteriaBuilder.array(register, userStudent, activity, student, term, classes, faculty));

        criteriaQuery.where(criteriaBuilder.equal(register.get("rollup"), false));

        Query query = s.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void submitRegister(JoinActivity joinActivity) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        s.save(joinActivity);
    }

    @Override
    public List<Object[]> getRegistersByUser(int userId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        List<Predicate> predicates = new ArrayList<>();

        Root<JoinActivity> register = criteriaQuery.from(JoinActivity.class);
        Join<JoinActivity, Activity> activity = register.join("activityId", JoinType.INNER);
        Join<Activity, Term> term = activity.join("termId", JoinType.INNER);

        criteriaQuery.select(criteriaBuilder.array(register, activity, term));

        predicates.add(criteriaBuilder.equal(register.get("userId"), userId));
        predicates.add(criteriaBuilder.equal(register.get("rollup"), false));

        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void removeRegister(JoinActivity joinActivity) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        s.remove(joinActivity);
    }

    @Override
    public JoinActivity getRegisterById(int registerId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("JoinActivity.findById");
        query.setParameter("id", registerId);
        return (JoinActivity) query.getSingleResult();
    }

    @Override
    public JoinActivity getRegisterByUserAndActivity(int userId, int activityId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        String hql = "FROM JoinActivity WHERE userId.id = :userId AND activityId.id = :activityId";
        Query<JoinActivity> query = s.createQuery(hql, JoinActivity.class);
        query.setParameter("userId", userId);
        query.setParameter("activityId", activityId);
        return query.uniqueResult();
    }
}
