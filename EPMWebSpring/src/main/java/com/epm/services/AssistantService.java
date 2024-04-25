/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.Assistant;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ACER
 */
public interface AssistantService extends UserDetailsService{
    Assistant getAssistantByUsername(String username);
    List<Assistant> getAssistants();
    void addAssistant(Assistant assist);
}
