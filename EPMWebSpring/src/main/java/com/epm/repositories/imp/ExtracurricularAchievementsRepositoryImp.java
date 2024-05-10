/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.repositories.ExtracurricularAchievementsRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class ExtracurricularAchievementsRepositoryImp implements ExtracurricularAchievementsRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> extraAchievementsByPeriod(int accountStudentId, String semester, String yearStudy) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root rJA = q.from(JoinActivity.class);
        Root rSS = q.from(ScoreStudent.class);
        Root rS = q.from(Score.class);
        
        q.where(b.equal(rJA.get("accountStudentId").as(Integer.class), accountStudentId));
        
        return null;
    }
    
}
