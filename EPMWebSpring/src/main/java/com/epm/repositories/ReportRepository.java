/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.MissingReport;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface ReportRepository {
    public List<MissingReport> getListReports();
    public List<MissingReport> getListReportsByFaculty(int facultyId);
    public List<Object[]> getLists();
}
