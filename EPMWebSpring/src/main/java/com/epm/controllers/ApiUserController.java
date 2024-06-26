/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.components.JwtService;
import com.epm.dto.response.ResponseStruct;
import com.epm.dto.response.UserResponse;
import com.epm.mapper.UserMapper;
import com.epm.pojo.Student;
import com.epm.pojo.User;
import com.epm.services.StudentService;
import com.epm.services.UserRoleService;
import com.epm.services.UserService;
import com.epm.utils.StatusResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @author ACER
 */
@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin()
public class ApiUserController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStruct<String> loginSubmit(@RequestBody HashMap<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (this.userService.authUser(username, password) == true) {
            String token = this.jwtService.generateTokenLogin(username);

            return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, token);
        }

        return new ResponseStruct(StatusResponse.FAIL_RESPONSE, "none-token");
    }

    @GetMapping(path = "/current-user")
    public ResponseStruct<UserResponse> getCurrentUser(Principal principal) {
        String username = principal.getName();
        User user = this.userService.getUserByUsername(username);
        UserResponse userResponse = this.userMapper.toUserResponse(user);
        return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, userResponse);
    }

    @PostMapping(value = "/process_register", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void processRegister(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file, HttpServletRequest request) {
        User u = new User();
        String email = params.get("email");
        if (!email.endsWith("@ou.edu.vn")) {
            throw new IllegalArgumentException("Email must end with '@ou.edu.vn'");
        }

        Student s = studentService.findStudentByEmail(email);

        if (s == null) {
            throw new IllegalArgumentException("Student not found for the given email.");
        }

        if (s.getUserId() == null) {
            u.setUsername(params.get("username"));
            String password = params.get("password");
            u.setPassword(this.passwordEncoder.encode(password));
            u.setUserRoleId(userRoleService.getURStudent());
            if (file.length > 0) {
                u.setFile(file[0]);
            }

            String randomCode = RandomString.make(64);
            u.setVerificationCode(randomCode);
            u.setActive(false);

            s.setUserId(u);

            this.userService.addUser(u);
            this.studentService.update(s);
            this.userService.sendVerificationEmail(u, getSiteURL(request), email);
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("code") String code) {
        String responseMessage;
        if (userService.verify(code)) {
            responseMessage = "<html><body>Verification successful. "
                    + "<a href=\"http://localhost:3000/login\">Click here to login</a></body></html>";
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(responseMessage);
        } else {
            responseMessage = "<html><body>Verification failed. "
                    + "<a href=\"http://localhost:3000/register\">Click here to register</a></body></html>";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_HTML).body(responseMessage);
        }
    }
}
