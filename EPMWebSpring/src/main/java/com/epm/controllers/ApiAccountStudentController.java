/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;


import com.epm.components.JwtService;
import com.epm.pojo.AccountStudent;
import com.epm.services.AccountStudentService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/account-student")
public class ApiAccountStudentController {

    @Autowired
    private BCryptPasswordEncoder passswordEncoder;
    @Autowired
    private AccountStudentService accountStudentService;
    @Autowired
    private JwtService jwtService;
    
    @PostMapping(path = "/register/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        AccountStudent as = new AccountStudent();
        as.setUsername(params.get("username"));
        String password = params.get("password");
        as.setPassword(this.passswordEncoder.encode(password));
        as.setUserRole("ROLE_STUDENT");
        if (file.length > 0)
            as.setFile(file[0]);
        
        this.accountStudentService.addAccountStudent(as);
    }
    
    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody AccountStudent as) {
        if (this.accountStudentService.authAccountStudent(as.getUsername(), as.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(as.getUsername());
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

}
