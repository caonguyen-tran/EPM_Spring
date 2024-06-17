/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Liked;
import com.epm.repositories.LikeRepository;
import javax.persistence.NoResultException;
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
public class LikeRepositoryImp implements LikeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Liked findByUserAndActivity(int userId, int activityId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Liked> q = b.createQuery(Liked.class);
        Root<Liked> r = q.from(Liked.class);
        q.select(r);

        q.where(b.and(b.equal(r.get("userId").get("id"), userId), b.equal(r.get("activityId").get("id"), activityId)));

        try {
            return s.createQuery(q).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

        @Override
        public long countByActivityId
        (int activityId
        
            ) {
        Session s = this.factory.getObject().getCurrentSession();
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);

            Root<Liked> root = cq.from(Liked.class);
            cq.select(cb.count(root));
            cq.where(cb.equal(root.get("activityId"), activityId));

            return s.createQuery(cq).getSingleResult();
        }

        @Override
        public boolean existsByUserIdAndActivityId
        (int userId, int activityId
        
            ) {
        Session s = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Long> q = b.createQuery(Long.class);

            Root<Liked> root = q.from(Liked.class);
            q.select(b.count(root));
            q.where(
                    b.and(
                            b.equal(root.get("userId"), userId),
                            b.equal(root.get("activityId"), activityId)
                    )
            );

            Long count = s.createQuery(q).getSingleResult();
            return count > 0;
        }

        @Override
        public void save
        (Liked like
        
            ) {
        Session s = this.factory.getObject().getCurrentSession();
            s.save(like);
        }

        @Override
        public void update
        (Liked like
        
            ) {
        Session s = this.factory.getObject().getCurrentSession();
            s.update(like);
        }

    }
