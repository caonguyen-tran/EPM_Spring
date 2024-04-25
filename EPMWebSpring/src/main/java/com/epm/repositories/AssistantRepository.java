/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.Assistant;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface AssistantRepository {
    Assistant getAssistantByUsername(String username);
    List<Assistant> getAssistants();
    void addAssistant(Assistant assist);
}
