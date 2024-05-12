/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.MissingReport;
import com.epm.repositories.ActivityRepository;
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
public class ActivityRepositoryImp implements ActivityRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Activity> getActivities() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Activity.findAll");
        return q.getResultList();
    }

    @Override
    public void createActivity(Activity activity) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.save(activity);
    }

    @Override
    public List<Activity> getActivitiesJoined(int accountStudentId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Activity.class);
        Root r = q.from(Activity.class);
        q.select(r);
        
        Join<Activity, JoinActivity> joiningActivities = r.join("joinActivitySet");
        
        q.where(b.equal(joiningActivities.get("accountStudentId").as(Integer.class), accountStudentId));
        
        return s.createQuery(q).getResultList();
    }

    @Override
    public List<Activity> getActivitiesMissingByAccountStudentId(int accountStudentId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Activity> q = b.createQuery(Activity.class);
        Root r = q.from(Activity.class);
        q.select(r);
        
        Join<Activity, MissingReport> missingActivities = r.join("missingReportSet");
        
        q.where(b.equal(missingActivities.get("accountStudentId").as(Integer.class), accountStudentId));
        
        return s.createQuery(q).getResultList();
    }
}
