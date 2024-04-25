/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.Assistant;
import com.epm.repositories.AssistantRepository;
import com.epm.services.AssistantService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service("assistantSevice")
public class AssistantServiceImp implements AssistantService{
    @Autowired
    private AssistantRepository assistantRepository;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Override
    public Assistant getAssistantByUsername(String username) {
        return this.assistantRepository.getAssistantByUsername(username);
    }

    @Override
    public List<Assistant> getAssistants() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assistant u = this.assistantRepository.getAssistantByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Assistant not found!");
        }
      
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ASSISTANT"));
        
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public void addAssistant(Assistant assist) {
        if (!assist.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(assist.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                assist.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(AssistantServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.assistantRepository.addAssistant(assist);
    }
    
}
