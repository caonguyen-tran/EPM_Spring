/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.dto.response;

/**
 *
 * @author ACER
 */
public class ScoreResponse {
    private Integer id;
    private String scoreName;
    private String description;
    private int scoreValue;
    private int numberOfScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getNumberOfScore() {
        return numberOfScore;
    }

    public void setNumberOfScore(int numberOfScore) {
        this.numberOfScore = numberOfScore;
    }
    
    
}
