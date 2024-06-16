/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Student;
import com.epm.repositories.StudentRepository;
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
 * @author ACER
 */
@Repository
@Transactional
public class StudentRepositoryImp implements StudentRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Student> getStudents() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Student.findAll");
        return q.getResultList();
    }

    @Override
    public void addStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student findById(int studentId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Student.findById");
        q.setParameter("id", studentId);
        return (Student) q.getSingleResult();
    }

    @Override
    public Student findByClassId(int classId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Student> q = b.createQuery(Student.class);
        Root r = q.from(Student.class);
        q.select(r);
        
        q.where(b.equal(r.get("classId"), classId));
        
        return s.createQuery(q).getSingleResult();
    }

    @Override
    public Student findStudentByEmail(String email) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Student.findByEmail");
        q.setParameter("email", email);
        return (Student) q.getSingleResult();
    }

    @Override
    public void update(Student student) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.update(student);
    }

}
