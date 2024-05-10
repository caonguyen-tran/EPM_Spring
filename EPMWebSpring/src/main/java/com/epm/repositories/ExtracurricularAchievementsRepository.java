/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import java.util.List;

/**
 *
 * @author Win11
 */
public interface ExtracurricularAchievementsRepository {
    List<Object[]> extraAchievementsByPeriod(int accountStudentId, String semester, String yearStudy);
}
