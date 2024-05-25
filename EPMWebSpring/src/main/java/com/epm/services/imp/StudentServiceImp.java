/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Student;
import com.epm.repositories.StudentRepository;
import com.epm.services.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public List<Student> getStudents() {
        return this.studentRepository.getStudents();
    }

    @Override
    public void addStudent(Student student) {
        this.studentRepository.addStudent(student);
    }

    @Override
    public Student findById(int studentId) {
        return this.studentRepository.findById(studentId);
    }

    @Override
    public Student findByClassId(int classId) {
        return this.studentRepository.findByClassId(classId);
    }
    
}
