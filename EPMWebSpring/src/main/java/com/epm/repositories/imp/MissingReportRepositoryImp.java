/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.Faculty;
import com.epm.pojo.MissingReport;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.repositories.MissingReportRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
public class MissingReportRepositoryImp implements MissingReportRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addMissingReport(MissingReport mr) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(mr);
    }

    @Override
    public List<Object[]> getListMissingReports(int facultyId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<MissingReport> missingReport = criteriaQuery.from(MissingReport.class);
        Join<MissingReport, Activity> activity = missingReport.join("activityId", JoinType.INNER);
        Join<MissingReport, User> user = missingReport.join("userId", JoinType.INNER);
        Join<Activity, Term> term = activity.join("termId", JoinType.INNER);
        Join<User, Student> student = user.join("student", JoinType.INNER);
        Join<Student, Classes> classes = student.join("classId", JoinType.INNER);
        Join<Classes, Faculty> faculty = classes.join("facultyId", JoinType.INNER);
        
        criteriaQuery.select(criteriaBuilder.array(missingReport, activity, user, term, student, classes, faculty));
        
        if(facultyId != 0){
            criteriaQuery.where(criteriaBuilder.equal(faculty.get("id"), facultyId));
        }
        
        Query q = s.createQuery(criteriaQuery);

        return q.getResultList();
    }
}
