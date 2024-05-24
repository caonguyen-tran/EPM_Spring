/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.AccountStudent;
import com.epm.repositories.AccountStudentRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Win11
 */
@Repository
@Transactional
public class AccountStudentRepositoryImp implements AccountStudentRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @Override
    public AccountStudent findByStudentId(int studentId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<AccountStudent> q = b.createQuery(AccountStudent.class);
        Root r = q.from(AccountStudent.class);
        q.select(r);
        
        q.where(b.equal(r.get("studentId"), studentId));

        return s.createQuery(q).getSingleResult();
    }

    @Override
    public AccountStudent findByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("AccountStudent.findByUsername");
        q.setParameter("username", username);
        return (AccountStudent) q.getSingleResult();
    }

    @Override
    public void addAccountStudent(AccountStudent accountStudent) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(accountStudent);
    }

    @Override
    public boolean authAccountStudent(String username, String password) {
        AccountStudent  u = this.findByUsername(username);
        return this.passEncoder.matches(password, u.getPassword());
    }
    
}
