/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Comment;
import com.epm.repositories.CommentRepository;
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
public class CommentRepositoryImp implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> findByActivityId(int activityId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Comment> r = q.from(Comment.class);

        q.multiselect(
                r.get("content"),
                r.get("createdDate"),
                r.get("image")
        );

        q.where(b.equal(r.get("activityId"), activityId));

        return s.createQuery(q).getResultList();
    }

    @Override
    public List<Comment> findByUserId(int userId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Comment.class);
        Root r = q.from(Comment.class);
        q.select(r);

        q.where(b.equal(r.get("userId"), userId));

        return s.createQuery(q).getResultList();
    }

    @Override
    public void save(Comment c) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(c);
    }

    @Override
    public void update(Comment c) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(c);
    }

    @Override
    public Comment findById(int commentParentId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Comment.findById");
        q.setParameter("id", commentParentId);

        return (Comment) q.getSingleResult();
    }

    @Override
    public void delete(Comment c) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(c);
    }

}
