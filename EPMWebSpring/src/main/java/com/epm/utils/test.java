/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

import com.epm.pojo.Activity;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ACER
 */
public class test {
    public static void main(String[] args) {
        Activity activity = new Activity();
        String dateString = "2024-05-01 11:30:00"; // Chuỗi cần chuyển đổi
        String pattern = "yyyy-MM-dd HH:mm:ss"; // Định dạng của chuỗi
        
        // Tạo đối tượng SimpleDateFormat với định dạng đã cho
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        
        try {
            // Parse chuỗi thành đối tượng java.util.Date
            java.util.Date parsedDate = dateFormat.parse(dateString);
            
            // Chuyển đổi java.util.Date thành Timestamp
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            
            System.out.println(timestamp);
            
            activity.setStartDate(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu có lỗi trong quá trình parse chuỗi
        }
        
        System.out.println(activity.getStartDate());
    }
}
