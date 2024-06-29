/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.MissingReport;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface MissingReportService {

    void addMissingReport(MissingReport mr);

    List<Object[]> getListMissingReports(int facultyId);

    List<Object[]> getListMRByStudent(int userId, int semesterId, String yearStudy);
    
    List<Object[]> listMissingReport(int semesterId, String yearStudy);
    
    Object[] getMRById(int mrId);
    
    MissingReport rejectMR(int missingReportId);
}
