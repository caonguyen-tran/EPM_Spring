/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.MissingReport;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface ReportService {
    public List<Object[]> getListReports(int facultyId);
//    public List<Object[]> getListReportsByFacultyId(int facultyId);
}
