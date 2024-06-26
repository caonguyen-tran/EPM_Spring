/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.JoinActivity;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface RegisterService {
    List<Object[]> getRegisters();
    void submitRegister(JoinActivity joinActivity);
    List<Object[]> getRegistersByUser(int userId);
    void removeRegister(JoinActivity joinActivity);
    JoinActivity getRegisterById(int registerId);
    JoinActivity getRegisterByUserAndActivity(int userId, int activityId);
}
