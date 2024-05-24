/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.AccountStudent;
import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.Faculty;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.repositories.JoinRepository;
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
public class JoinRepositoryImp implements JoinRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public List<JoinActivity> getParticipateByFaculty(int facultyId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getParticipates(String activityId, String facultyId, String classId, String semesterId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        List<Predicate> predicates = new ArrayList<>();

        Root<JoinActivity> join = criteriaQuery.from(JoinActivity.class);
        Join<JoinActivity, AccountStudent> accountStudent = join.join("accountStudentId", JoinType.INNER);
        Join<JoinActivity, Activity> activity = join.join("activityId", JoinType.INNER);
        Join<Activity, Semester> semester = activity.join("semesterId", JoinType.INNER);
        Join<AccountStudent, Student> student = accountStudent.join("studentId", JoinType.INNER);
        Join<Activity, Term> term = activity.join("termId", JoinType.INNER);
        Join<Student, Classes> classes = student.join("classId", JoinType.INNER);
        Join<Classes, Faculty> faculty = classes.join("facultyId", JoinType.INNER);

        criteriaQuery.select(criteriaBuilder.array(join, accountStudent, activity, semester, student, term, classes, faculty));

        predicates.add(criteriaBuilder.equal(join.get("rollup"), true));

        if (activityId != null && !activityId.isEmpty()) {
            predicates.add(criteriaBuilder.equal(activity.get("id"), Integer.parseInt(activityId)));
        }
        
        if(facultyId != null && !facultyId.isEmpty()){
            predicates.add(criteriaBuilder.equal(faculty.get("id"), Integer.parseInt(facultyId)));
        }
        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
