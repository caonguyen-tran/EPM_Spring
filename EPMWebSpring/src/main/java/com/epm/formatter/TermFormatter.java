/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.formatter;

import com.epm.pojo.Term;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ACER
 */
public class TermFormatter implements Formatter<Term>{

    @Override
    public String print(Term term, Locale locale) {
        return String.valueOf(term.getId());
    }

    @Override
    public Term parse(String id, Locale locale) throws ParseException {
        Term c = new Term();
        c.setId(Integer.parseInt(id));
        
        return c;
    }
    
}
