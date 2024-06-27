/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.Faculty;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface FacultyRepository {
    List<Faculty> getFaculties();
    
    Faculty findById(int facultyId);
}
