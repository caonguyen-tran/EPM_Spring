/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.services.StatisticsService;
import com.epm.utils.AchievementStatisticsDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/statistics")
public class ApiStatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/class/{classId}/achievements")
    public ResponseEntity<List<AchievementStatisticsDTO>> getAchievementStatisticsByClass(@PathVariable int classId) {
        List<AchievementStatisticsDTO> statistics = statisticsService.getAchievementStatisticsByClass(classId);
        return ResponseEntity.ok(statistics);
    }
}
