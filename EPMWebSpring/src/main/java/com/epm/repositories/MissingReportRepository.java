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

    void addMissingReport(MissingReport mr);

    public List<Object[]> getListMissingReports(int facultyId);

    List<Object[]> getListMRByStudent(int userId, int semesterId, String yearStudy);
    
    List<Object[]> listMissingReport(int semesterId, String yearStudy);
    
    Object[] getMRById(int mrId);

    public List<MissingReport> getListMissingReportByUser(int userId, int semesterId);

    public MissingReport getMissingReportByUserAndActivity(int userId, int activityId);
}
