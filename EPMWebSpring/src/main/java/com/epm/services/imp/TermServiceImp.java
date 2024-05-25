/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Term;
import com.epm.repositories.TermRepository;
import com.epm.services.TermService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class TermServiceImp implements TermService{
    @Autowired
    private TermRepository termRepository;
    
    @Override
    public List<Term> getTerms() {
        return this.termRepository.getTerms();
    }

    @Override
    public Term findById(int termId) {
        return this.termRepository.findById(termId);
    }
}
