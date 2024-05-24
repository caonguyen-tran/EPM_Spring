/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.Semester;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface SemesterRepository {
    List<Semester> getSemesters();
    
    List<Semester> getSemestersByStudyYear(String studyYear);
    
    Semester findById(int semesterId);
}
