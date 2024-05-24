/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.MissingReport;
import com.epm.repositories.ReportRepository;
import com.epm.services.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ReportSeviceImp implements ReportService{
    @Autowired
    private ReportRepository reportRepository;
    
    @Override
    public List<Object[]> getListReports(int facultyId) {
        return this.reportRepository.getListReports(facultyId);
    }
}
