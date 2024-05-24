/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.MissingReport;
import com.epm.repositories.MissingReportRepository;
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
public class MissingReportRepositoryImp implements MissingReportRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public MissingReport createMissingReport(MissingReport mr) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(mr);
        
        return mr;
    }
}
