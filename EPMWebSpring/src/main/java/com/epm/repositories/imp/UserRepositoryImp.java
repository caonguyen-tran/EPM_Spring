/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Student;
import com.epm.pojo.User;
import com.epm.repositories.UserRepository;
import java.util.List;
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
public class UserRepositoryImp implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);

        try {
            return (User) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        if (u == null) {
            return false;
        }
        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public void addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
    }

    @Override
    public List<User> getAssistantUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root r = q.from(User.class);
        q.select(r);

        q.where(b.equal(r.get("userRoleId"), 2));

        return s.createQuery(q).getResultList();
    }

    @Override
    public User findByStudentId(int studentId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Student> studentQuery = s.createNamedQuery("Student.findById", Student.class);
        studentQuery.setParameter("id", studentId);
        Student student = studentQuery.getSingleResult();

        Query<User> userQuery = s.createNamedQuery("User.findById", User.class);
        userQuery.setParameter("id", student.getUserId().getId());

        return userQuery.getSingleResult();
    }

    @Override
    public User findByVerificationCode(String verificationCode) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByVerificationCode");
        q.setParameter("verificationCode", verificationCode);

        return (User) q.getSingleResult();
    }

    @Override
    public void save(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);
    }

    @Override
    public void update(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(u);
    }

    @Override
    public User findByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Student> studentQuery = s.createNamedQuery("Student.findByEmail", Student.class);
        studentQuery.setParameter("email", email);
        Student student = studentQuery.getSingleResult();

        Query<User> userQuery = s.createNamedQuery("User.findById", User.class);
        userQuery.setParameter("id", student.getUserId().getId());

        return userQuery.getSingleResult();
    }

    @Override
    public User findById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findById");
        q.setParameter("id", id);

        return (User) q.getSingleResult();
    }
}
