/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Classes;
import com.epm.repositories.ClassesRepository;
import com.epm.services.ClassesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ClassesServiceImp implements ClassesService{
    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public List<Classes> getClasses() {
        return this.classesRepository.getClasses();
    }
}
