/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

import com.epm.pojo.Student;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author ACER
 */
public class HibernateTest {

    public static void main(String[] args) {
        try(Session s = HibernateUtils.getFactory().getCurrentSession()){
            
            System.out.println("HUHU bi loi khong chay duoc!");
        }
    }
}
