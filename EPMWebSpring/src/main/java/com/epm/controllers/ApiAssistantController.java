/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Assistant;
import com.epm.services.AssistantService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ApiAssistantController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private AssistantService assistantService;
    
    @PostMapping(path = "/assist", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file){
        Assistant u = new Assistant();
        u.setUsername(params.get("username"));
        u.setEmail(params.get("email"));
        String password = params.get("password");
        u.setPassword(this.passwordEncoder.encode(password));
        if (file.length > 0)
        {
            u.setFile(file[0]);
        }
        
        this.assistantService.addAssistant(u);
    }
    
    @GetMapping(path="/assist", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Assistant>> list(){
        return new ResponseEntity<>(this.assistantService.getAssistants(), HttpStatus.OK);
    }
}
