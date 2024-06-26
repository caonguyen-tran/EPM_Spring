/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.Classes;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface ClassRepository {
    Classes findById(int classId);
    
    public List<Classes> getClasses();
    
    List<Object[]> getClassFaculty();
}
