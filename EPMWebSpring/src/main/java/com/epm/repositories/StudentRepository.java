/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import java.util.List;
import com.epm.pojo.Student;
/**
 *
 * @author ACER
 */
public interface StudentRepository {
    List<Student> getStudents();
    
    void addStudent(Student student);
    
    Student findById(int studentId);
    
    Student findByClassId(int classId);
}
