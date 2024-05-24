/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.MissingReport;
import com.epm.repositories.MissingReportRepository;
import com.epm.services.MissingReportService;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service
public class MissingReportServiceImp implements MissingReportService {

    @Autowired
    private MissingReportRepository missingReportRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public MissingReport createMissingReport(MissingReport mr) {
        if (mr.getFile() != null && !mr.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(mr.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                mr.setProofJoining(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(MissingReportServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.missingReportRepo.createMissingReport(mr);
    }

}