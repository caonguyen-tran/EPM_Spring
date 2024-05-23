/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Classes;
import com.epm.repositories.ClassRepository;
import com.epm.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service
public class ClassServiceImp implements ClassService{
    @Autowired
    private ClassRepository classRepo;

    @Override
    public Classes findById(int classId) {
        return this.classRepo.findById(classId);
    }
    
}
