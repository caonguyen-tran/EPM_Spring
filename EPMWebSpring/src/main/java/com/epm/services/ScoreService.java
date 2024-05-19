/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import java.util.Map;

/**
 *
 * @author Win11
 */
public interface ScoreService {
    Map<String, Integer> getTotalScoresByTerm(int accountStudentId);
    int getTotalScores(int accountStudentId);
}
