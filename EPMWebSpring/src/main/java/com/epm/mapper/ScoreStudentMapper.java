/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.ScoreStudentResponse;
import com.epm.pojo.ScoreStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class ScoreStudentMapper {
    @Autowired
    private JoinActivityMapper joinActivityMapper;
    
    @Autowired
    private ScoreMapper scoreMapper;
    
    public ScoreStudentResponse toScoreStudentResponse(ScoreStudent scoreStudent){
        ScoreStudentResponse ssr = new ScoreStudentResponse();
        ssr.setId(scoreStudent.getId());
        ssr.setJoinActivityResponse(joinActivityMapper.toJoinActivityResponse(scoreStudent.getJoinActivityId()));
        ssr.setScoreResponse(scoreMapper.toScoreResponse(scoreStudent.getScoreId()));
        return ssr;
    }
}
