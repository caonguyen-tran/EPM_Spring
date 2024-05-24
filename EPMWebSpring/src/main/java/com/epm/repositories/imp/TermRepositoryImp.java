/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Term;
import com.epm.repositories.TermRepository;
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
public class TermRepositoryImp implements TermRepository{
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;
    
    @Override
    public List<Term> getTerms() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Term.findAll");
        return query.getResultList();
    }

    @Override
    public Term findById(int termId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Term.findById");
        q.setParameter("id", termId);
        
        return (Term) q.getSingleResult();
    }
}
