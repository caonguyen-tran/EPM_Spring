/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.MissingReport;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface MissingReportRepository {
    List<MissingReport> getMissingReportByAccountStudentId(int accountStudentId);
    
    void createMissingReport(MissingReport mr);
}
