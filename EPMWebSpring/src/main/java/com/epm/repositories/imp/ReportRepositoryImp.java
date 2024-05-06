/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.AccountStudent;
import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.Faculty;
import com.epm.pojo.MissingReport;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.repositories.ReportRepository;
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
public class ReportRepositoryImp implements ReportRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public List<MissingReport> getListReports() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();

        Query query = s.createNamedQuery("MissingReport.findAll");

        return query.getResultList();
    }

    @Override
    public List<MissingReport> getListReportsByFaculty(int facultyId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getLists() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<MissingReport> rootA = criteriaQuery.from(MissingReport.class);
        Join<MissingReport, Activity> joinB = rootA.join("activityId", JoinType.INNER);
        Join<MissingReport, AccountStudent> joinC = rootA.join("accountStudentId", JoinType.INNER);
        Join<Activity, Term> joinD = joinB.join("termId", JoinType.INNER);
        Join<AccountStudent, Student> joinE = joinC.join("studentId", JoinType.INNER);
        
        
        criteriaQuery.select(criteriaBuilder.array(rootA, joinB, joinC, joinD, joinE));
        
        Query q = s.createQuery(criteriaQuery);

        return q.getResultList();
    }

}
