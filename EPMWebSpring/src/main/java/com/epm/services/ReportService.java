
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.utils.StudentReportDTO;
import com.epm.pojo.MissingReport;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface ReportService {
    List<StudentReportDTO> getStatsScoreDetail(int studentId);
    public List<Object[]> getListReports(int facultyId);
}

