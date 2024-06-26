/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Classes;
import com.epm.repositories.ClassRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class ClassRepositoryImp implements ClassRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Classes findById(int classId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Classes.findById");
        q.setParameter("id", classId);
        return (Classes) q.getSingleResult();
    }

    @Override
    public List<Classes> getClasses() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Classes.findAll");
        return q.getResultList();
    }

    @Override
    public List<Object[]> getClassFaculty() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Object[].class);
        Root r = q.from(Classes.class);
        q.select(r);

        q.multiselect(r, r.get("facultyId"));

        return s.createQuery(q).getResultList();
    }

}
