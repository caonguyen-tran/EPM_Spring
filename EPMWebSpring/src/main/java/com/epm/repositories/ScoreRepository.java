/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.Score;
import java.util.List;

/**
 *
 * @author MyLaptop
 */
public interface ScoreRepository {

    List<Score> findAll();

    Score findById(int scoreId);

    List<Score> findByActivityId(int activityId);
}
