/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.UserRole;
import com.epm.repositories.UserRoleRepository;
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
public class UserRoleRepositoryImp implements UserRoleRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public UserRole getURStudent() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("UserRole.findById");
        q.setParameter("id", 3);
        
        return (UserRole) q.getSingleResult();
    }

    @Override
    public UserRole getURAssistant() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("UserRole.findById");
        q.setParameter("id", 2);
        
        return (UserRole) q.getSingleResult();
    }

    @Override
    public UserRole getURAdmin() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("UserRole.findById");
        q.setParameter("id", 1);
        
        return (UserRole) q.getSingleResult();
    }
    
}
