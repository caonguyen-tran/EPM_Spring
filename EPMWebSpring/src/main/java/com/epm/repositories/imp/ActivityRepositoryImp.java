/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.MissingReport;
import com.epm.pojo.Semester;
import com.epm.repositories.ActivityRepository;
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
    public List<Object[]> getActivitiesJoined(int userId, int semesterId, String yearStudy) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Activity> rootActivity = cq.from(Activity.class);
        Join<Activity, JoinActivity> joinActivity = rootActivity.join("joinActivitySet");

        cq.multiselect(rootActivity, rootActivity.get("semesterId"), rootActivity.get("termId"), joinActivity);

        Predicate userIdPredicate = cb.equal(joinActivity.get("userId"), userId);

        Predicate rollUpPredicate = cb.equal(joinActivity.get("rollup"), true);

        Subquery<Integer> subqueryMaxSemesterId = cq.subquery(Integer.class);
        Root<Semester> rootSemesterSub = subqueryMaxSemesterId.from(Semester.class);
        subqueryMaxSemesterId.select(cb.max(rootSemesterSub.get("id")));

        Predicate semesterIdPredicate = null;
        if (semesterId > 0) {
            semesterIdPredicate = cb.equal(rootActivity.get("semesterId"), semesterId);
        }

        if (semesterId <= 0 && yearStudy.isEmpty()) {
            semesterIdPredicate = cb.equal(rootActivity.get("semesterId"), subqueryMaxSemesterId);
        }

        if (semesterId <= 0 && !yearStudy.isEmpty()) {
            Subquery<Integer> subquerySemesterIdByYearStudy = cq.subquery(Integer.class);
            Root<Semester> rootSemesterByYearStudy = subquerySemesterIdByYearStudy.from(Semester.class);
            subquerySemesterIdByYearStudy.select(rootSemesterByYearStudy.get("id"));
            subquerySemesterIdByYearStudy.where(cb.equal(rootSemesterByYearStudy.get("yearStudy"), yearStudy));

            semesterIdPredicate = cb.in(rootActivity.get("semesterId")).value(subquerySemesterIdByYearStudy);
        }

        cq.where(cb.and(userIdPredicate, rollUpPredicate, semesterIdPredicate));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public List<Activity> findByTermId(int termId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Activity.findByTermId");
        q.setParameter("termId", termId);

        return q.getResultList();

    }

    @Override
    public Activity findById(int activityId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Activity.findById");
        q.setParameter("id", activityId);

        return (Activity) q.getSingleResult();
    }

    @Override
    public List<Activity> findBySemesterId(int semesterId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Activity> q = b.createQuery(Activity.class);
        Root r = q.from(Activity.class);
        q.select(r);

        q.where(b.equal(r.get("semesterId"), semesterId));

        return s.createQuery(q).getResultList();
    }

    @Override
    public List<Activity> getActivitiesMissingByUserId(int userId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Activity> q = b.createQuery(Activity.class);
        Root r = q.from(Activity.class);
        q.select(r);

        Join<Activity, MissingReport> missingActivities = r.join("missingReportSet");

        q.where(b.equal(missingActivities.get("userId"), userId));

        return s.createQuery(q).getResultList();
    }

    @Override
    public void update(Activity activity) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.update(activity);
    }

    @Override
    public void delete(Activity activity) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.delete(activity);
    }

    @Override
    public Object[] getActivity(int activityId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root r = q.from(Activity.class);
        q.select(r);

        q.multiselect(r, r.get("termId"), r.get("semesterId"), r.get("facultyId"));

        q.where(b.equal(r.get("id"), activityId));

        try {
            return s.createQuery(q).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Object[]> getAllActivities() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root r = q.from(Activity.class);
        q.select(r);
        
        q.multiselect(r, r.get("termId"), r.get("semesterId"), r.get("facultyId"));

        try {
            return s.createQuery(q).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
