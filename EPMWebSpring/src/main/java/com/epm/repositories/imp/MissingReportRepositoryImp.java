/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.Faculty;
import com.epm.pojo.MissingReport;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.repositories.MissingReportRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
 * @author Win11
 */
@Repository
@Transactional
public class MissingReportRepositoryImp implements MissingReportRepository {

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

        if (facultyId != 0) {
            criteriaQuery.where(criteriaBuilder.equal(faculty.get("id"), facultyId));
        }

        Query q = s.createQuery(criteriaQuery);

        return q.getResultList();
    }

    @Override
    public List<Object[]> getListMRByStudent(int userId, int semesterId, String yearStudy) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<MissingReport> rootMissingReport = cq.from(MissingReport.class);
        Join<MissingReport, Activity> join = rootMissingReport.join("activityId");

        cq.multiselect(rootMissingReport, join);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(rootMissingReport.get("userId"), userId));

        if (semesterId > 0) {
            predicates.add(cb.equal(join.get("semesterId"), semesterId));
        } else if (yearStudy != null && !yearStudy.isEmpty()) {
            Subquery<Integer> subquerySemesterIdByYearStudy = cq.subquery(Integer.class);
            Root<Semester> rootSemesterByYearStudy = subquerySemesterIdByYearStudy.from(Semester.class);
            subquerySemesterIdByYearStudy.select(rootSemesterByYearStudy.get("id"));
            subquerySemesterIdByYearStudy.where(cb.equal(rootSemesterByYearStudy.get("yearStudy"), yearStudy));
            predicates.add(cb.in(join.get("semesterId")).value(subquerySemesterIdByYearStudy));
        } else {

        }

        cq.where(predicates.toArray(new Predicate[0]));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public List<Object[]> listMissingReport(int semesterId, String yearStudy) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<MissingReport> rootMissingReport = cq.from(MissingReport.class);
        Join<MissingReport, Activity> join = rootMissingReport.join("activityId");

        cq.multiselect(rootMissingReport, join, rootMissingReport.get("userId").get("student"), 
                rootMissingReport.get("userId").get("student").get("classId"), rootMissingReport.get("userId").get("student").get("classId").get("facultyId"));

        List<Predicate> predicates = new ArrayList<>();

        if (semesterId > 0) {
            predicates.add(cb.equal(join.get("semesterId"), semesterId));
        } else if (yearStudy != null && !yearStudy.isEmpty()) {
            Subquery<Integer> subquerySemesterIdByYearStudy = cq.subquery(Integer.class);
            Root<Semester> rootSemesterByYearStudy = subquerySemesterIdByYearStudy.from(Semester.class);
            subquerySemesterIdByYearStudy.select(rootSemesterByYearStudy.get("id"));
            subquerySemesterIdByYearStudy.where(cb.equal(rootSemesterByYearStudy.get("yearStudy"), yearStudy));
            predicates.add(cb.in(join.get("semesterId")).value(subquerySemesterIdByYearStudy));
        } else {

        }

        cq.where(predicates.toArray(new Predicate[0]));

        return session.createQuery(cq).getResultList();
    }
    
    @Override
    public Object[] getMRById(int mrId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<MissingReport> rootMissingReport = cq.from(MissingReport.class);
        Join<MissingReport, Activity> join = rootMissingReport.join("activityId");

        cq.multiselect(rootMissingReport, join, rootMissingReport.get("userId").get("student"), 
                rootMissingReport.get("userId").get("student").get("classId"), 
                rootMissingReport.get("userId").get("student").get("classId").get("facultyId"),
                rootMissingReport.get("userId"));
        
        cq.where(cb.equal(rootMissingReport.get("id"), mrId));
        
        return session.createQuery(cq).getSingleResult();
    }
}
