/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.JoinActivityResponse;
import com.epm.pojo.JoinActivity;

/**
 *
 * @author ACER
 */
public class JoinActivityMapper {
    
    public JoinActivityResponse toJoinActivity(JoinActivity joinActivity){
        JoinActivityResponse rs = new JoinActivityResponse();
        rs.setId(joinActivity.getId());
        rs.setDateRegister(joinActivity.getDateRegister());
        rs.setProofJoining(joinActivity.getProofJoining());
        rs.setAccept(joinActivity.getAccept());
        rs.setRollup(joinActivity.getRollup());
        rs.setNote(joinActivity.getNote());
        rs.setActivity(joinActivity.getActivityId());
        rs.setUser(joinActivity.getUserId());
        return rs;
    }
}
