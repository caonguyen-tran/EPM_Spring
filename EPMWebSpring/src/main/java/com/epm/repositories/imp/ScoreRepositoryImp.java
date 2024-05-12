/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Term;
import com.epm.repositories.ScoreRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MyLaptop
 */
@Repository
@Transactional
public class ScoreRepositoryImp implements ScoreRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getScoresByTerms() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root rS = q.from(Score.class);
        Join<Score, ScoreStudent> sSjoin = rS.join("scoreStudentSet");
        Root rAc = q.from(Activity.class);
        Root rT = q.from(Term.class);
        
        q.multiselect(rT.get("id"), rT.get("name"), b.sum(rS.get("scoreValue")));
        
        List<Predicate> pre = new ArrayList<>();
        pre.add(b.equal(rSS.get(""), rSS));
        
        return null;
    }
    
}
