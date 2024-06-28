/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Student;
import com.epm.services.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/student")
public class ApiStudentController {
  
    @Autowired
    private StudentService studentService;
    
    @GetMapping(path = "/{classId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> getAllStudents(@PathVariable(value = "classId") int classId){
        return new ResponseEntity<>(this.studentService.getListStudents(classId), HttpStatus.OK);
    }
    
   @GetMapping(path = "/student-detail/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
   @CrossOrigin
   public ResponseEntity<Student> getStudent(@PathVariable(value = "studentId") int studentId) {
       Student student = studentService.findById(studentId);
       if (student != null) {
           return new ResponseEntity<>(student, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
}
