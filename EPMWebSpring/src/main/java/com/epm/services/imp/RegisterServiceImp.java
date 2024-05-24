/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.repositories.RegisterRepository;
import com.epm.services.RegisterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class RegisterServiceImp implements RegisterService{
    @Autowired
    private RegisterRepository registerRepository;
    
    @Override
    public List<Object[]> getRegisters() {
        return this.registerRepository.getRegisters();
    }
}
