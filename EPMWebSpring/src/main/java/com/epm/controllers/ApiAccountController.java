/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.AccountStudent;
import com.epm.pojo.Student;
import com.epm.services.AccountService;
import com.nimbusds.jose.JOSEException;
import com.epm.components.JwtService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api")
public class ApiAccountController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
//    @PostMapping(path = "/add-account",
//            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addAccount(@RequestParam Map<String, String> data, @RequestPart MultipartFile file){
//        AccountStudent s = new AccountStudent();
//        s.setUsername(data.get("username"));
//        s.setPassword(passwordEncoder.encode(data.get("password")));
//        s.setFile(file);
//        Student student = new Student();
//        student.setId(5);
//        s.setStudentId(student);
//        this.accountService.addAccountStudent(s);
//    }
    @PostMapping(path = "/login", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<String> login(@RequestParam HashMap<String, String> data) throws JOSEException {
        String username = data.get("username");
        String password = data.get("password");
        if (this.accountService.authUser(username, password) == true) {
            String token = this.jwtService.generateToken(username);
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
}
