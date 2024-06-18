/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.User;
import com.epm.repositories.UserRepository;
import com.epm.services.UserService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service("userDetailsService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public void addUser(User user) {
        if (!user.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.userRepo.addUser(user);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public List<User> getAssistantUsers() {
        return this.userRepo.getAssistantUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRoleId().getUserRole()));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public User findByStudentId(int studentId) {
        return this.userRepo.findByStudentId(studentId);
    }

    @Override
    public void sendVerificationEmail(User user, String siteURL, String email) {
        String toAddress = email;
        String fromAddress = "nhanphela003@gmail.com";
        String senderName = "Email Confirm Registration";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            helper.setTo(toAddress);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            helper.setSubject(subject);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/api/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        try {
            helper.setText(content, true);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        mailSender.send(message);
    }

    @Override
    public User findByVerificationCode(String verificationCode) {
        return this.userRepo.findByVerificationCode(verificationCode);
    }
    
    @Override
    public void save(User u) {
        this.userRepo.save(u);
    }
    
    @Override
    public void update(User u) {
        this.userRepo.update(u);
    }

    @Override
    public boolean verify(String verificationCode) {
        User u = userRepo.findByVerificationCode(verificationCode);
        
        if(u == null || u.getActive() == true){
            return false;
        } else{
            u.setVerificationCode(null);
            u.setActive(true);
            userRepo.update(u);
            
            return true;
        }
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return this.userRepo.findById(id);
    }

}
