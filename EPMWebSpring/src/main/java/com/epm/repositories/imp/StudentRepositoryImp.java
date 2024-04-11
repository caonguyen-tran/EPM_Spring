/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories.imp;

import com.epm.pojo.Student;
import com.epm.repositories.StudentRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public class StudentRepositoryImp implements StudentRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Student> getStudents() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Student.findAll");
        return q.getResultList();
    }
}
