/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.Term;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface TermService {
    List<Term> getTerms();

    Term findById(int termId);
}
