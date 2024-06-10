/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.ScoreStudent;
import com.epm.repositories.ScoreStudentRepository;
import com.epm.services.ScoreStudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ScoreStudentServiceImp implements ScoreStudentService{
    @Autowired
    private ScoreStudentRepository scoreStudentRepository;

    @Override
    public ScoreStudent createScoreStudent(ScoreStudent scoreStudent) {
        return this.scoreStudentRepository.createScoreStudent(scoreStudent);
    }

    @Override
    public int createMultipleScoreStudent(List<JoinActivity> listJoinActivities, int scoreId) {
        return this.scoreStudentRepository.createMultipleScoreStudent(listJoinActivities, scoreId);
    }
}
