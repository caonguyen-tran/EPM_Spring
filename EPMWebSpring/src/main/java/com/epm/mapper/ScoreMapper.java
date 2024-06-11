/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.ScoreResponse;
import com.epm.pojo.Score;
import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class ScoreMapper {
    
    public ScoreResponse toScoreResponse(Score score){
        ScoreResponse sr = new ScoreResponse();
        sr.setId(score.getId());
        sr.setScoreName(score.getScoreName());
        sr.setDescription(score.getDescription());
        sr.setScoreValue(score.getScoreValue());
        sr.setNumberOfScore(score.getNumberOfScore());
        return sr;
    }
}
