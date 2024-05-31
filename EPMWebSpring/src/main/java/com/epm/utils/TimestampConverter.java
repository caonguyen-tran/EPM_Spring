/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ACER
 */
public class TimestampConverter {
    

    public static Timestamp convertStringToTimestamp(String date) throws ParseException {
        String pattern = "yyyy-MM-dd'T'HH:mm";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        java.util.Date parsedDate = dateFormat.parse(date);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        return timestamp;
    }
}
