/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Faculty;
import com.epm.repositories.FacultyRepository;
import com.epm.services.FacultyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class FacultyServiceImp implements FacultyService{
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Override
    public List<Faculty> getFaculties() {
        return this.facultyRepository.getFaculties();
    }
}
