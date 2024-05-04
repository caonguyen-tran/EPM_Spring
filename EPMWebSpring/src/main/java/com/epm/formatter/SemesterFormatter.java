/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.formatter;

import com.epm.pojo.Semester;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ACER
 */
public class SemesterFormatter implements Formatter<Semester>{

    @Override
    public String print(Semester s, Locale locale) {
        return String.valueOf(s.getId());
    }

    @Override
    public Semester parse(String id, Locale locale) throws ParseException {
        Semester s = new Semester();
        
        s.setId(Integer.parseInt(id));
        return s;
    }
}
