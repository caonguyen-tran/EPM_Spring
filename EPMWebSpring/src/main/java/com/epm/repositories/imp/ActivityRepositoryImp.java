/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.repositories.ActivityRepository;
import java.util.List;
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
}
