/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.JoinActivity;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface RegisterRepository {
    List<Object[]> getRegisters();

    public void submitRegister(JoinActivity joinActivity);

    public List<Object[]> getRegistersByUser(int userId);

    public void removeRegister(JoinActivity joinActivity);

    public JoinActivity getRegisterById(int registerId);
}
