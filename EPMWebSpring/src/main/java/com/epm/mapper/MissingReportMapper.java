/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.MissingReportResponse;
import com.epm.pojo.MissingReport;
import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class MissingReportMapper {
    public MissingReportResponse toMissingReportReponse(MissingReport missingReport){
        MissingReportResponse mrr = new MissingReportResponse();
        mrr.setId(missingReport.getId());
        mrr.setCreatedDate(missingReport.getCreatedDate());
        mrr.setNote(missingReport.getNote());
        mrr.setProofJoining(missingReport.getProofJoining());
        mrr.setStatus(missingReport.getStatus());
        mrr.setActivity(missingReport.getActivityId());
        return mrr;
    }
}
