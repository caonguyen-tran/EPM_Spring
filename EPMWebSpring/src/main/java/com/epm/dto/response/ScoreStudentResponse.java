/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.dto.response;

/**
 *
 * @author ACER
 */
public class ScoreStudentResponse {
    private Integer id;
    private JoinActivityResponse joinActivityResponse;
    private ScoreResponse scoreResponse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JoinActivityResponse getJoinActivityResponse() {
        return joinActivityResponse;
    }

    public void setJoinActivityResponse(JoinActivityResponse joinActivityResponse) {
        this.joinActivityResponse = joinActivityResponse;
    }

    public ScoreResponse getScoreResponse() {
        return scoreResponse;
    }

    public void setScoreResponse(ScoreResponse scoreResponse) {
        this.scoreResponse = scoreResponse;
    }
}
