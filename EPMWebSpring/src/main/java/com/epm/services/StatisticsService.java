/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.utils.AchievementStatisticsDTO;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface StatisticsService {
    List<AchievementStatisticsDTO> getAchievementStatisticsByClass(int classId);
}
