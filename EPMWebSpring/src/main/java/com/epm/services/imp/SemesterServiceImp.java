/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Semester;
import com.epm.repositories.SemesterRepository;
import com.epm.services.SemesterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class SemesterServiceImp implements SemesterService{
    @Autowired
    private SemesterRepository semesterRepository;
    
    @Override
    public List<Semester> getSemesters() {
        return this.semesterRepository.getSemesters();
    }

    @Override
    public List<Semester> getSemestersByStudyYear(String studyYear) {
        return this.semesterRepository.getSemestersByStudyYear(studyYear);
    }

    @Override
    public Semester findById(int semesterId) {
        return this.semesterRepository.findById(semesterId);
    }

    @Override
    public List<Semester> findBySemesterName(String name) {
        return this.semesterRepository.findBySemesterName(name);
    }
}
