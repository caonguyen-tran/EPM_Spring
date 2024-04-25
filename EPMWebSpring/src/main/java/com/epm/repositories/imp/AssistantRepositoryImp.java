/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Assistant;
import com.epm.repositories.AssistantRepository;
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
public class AssistantRepositoryImp implements AssistantRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Assistant getAssistantByUsername(String username) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query query = s.createQuery("FROM Assistant WHERE username = :username");
        return (Assistant) query.getSingleResult();
    }

    @Override
    public List<Assistant> getAssistants() {
        Session s = sessionFactory.getObject().getCurrentSession();

        Query query = s.createNamedQuery("Assistant.findAll");
        return query.getResultList();
    }

    @Override
    public void addAssistant(Assistant assist) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.save(assist);
    }

}
