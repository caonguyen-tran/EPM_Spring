/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.AccountStudent;
import com.epm.repositories.AccountRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
public class AccountRepositoryImp implements AccountRepository{
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public AccountStudent getUserByUsername(String username) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("AccountStudent.findByUsername");
        query.setParameter("username", username);
        return (AccountStudent) query.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        AccountStudent a = this.getUserByUsername(username);
        return passwordEncoder.matches(password, a.getPassword());
    }

    @Override
    public AccountStudent addAccountStudent(AccountStudent accountStudent) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        s.save(accountStudent);
        return accountStudent;
    }
    
}
