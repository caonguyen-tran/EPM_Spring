/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.dto.response.CommentResponse;
import com.epm.pojo.Activity;
import com.epm.pojo.Comment;
import com.epm.pojo.JoinActivity;
import com.epm.repositories.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
    public List<Comment> findByActivityId(int activityId) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Comment.class);
            Root root = criteriaQuery.from(Comment.class);
            criteriaQuery.select(root);

            List<Predicate> predicates = new ArrayList();
            predicates.add(criteriaBuilder.equal(root.get("activityId"), activityId));
            predicates.add(criteriaBuilder.isNull(root.get("commentParent")));

            criteriaQuery.where(predicates.toArray(Predicate[]::new));

            Query query = s.createQuery(criteriaQuery);

            List<Comment> comments = query.list();

            return comments;
        } catch (Exception ex) {
            return null;
        }
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

    @Override
    public List<Comment> getComments(Activity activity) {
        Session s = this.factory.getObject().getCurrentSession();
        return null;
    }

    @Override
    public void updateIsParent(Comment comment) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(comment);
    }

    @Override
    public List<Comment> getCommentsChild(int commentParentId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root root = criteriaQuery.from(Comment.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.equal(root.get("commentParent"), commentParentId));

        Query query = s.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
