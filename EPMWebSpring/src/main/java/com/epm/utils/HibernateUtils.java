/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

import com.epm.pojo.Activity;
import com.epm.pojo.Assistant;
import com.epm.pojo.Classes;
import com.epm.pojo.Comment;
import com.epm.pojo.Faculty;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Liked;
import com.epm.pojo.MissingReport;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import com.epm.pojo.UserRole;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class HibernateUtils {
    private static final SessionFactory factory;
    
    static{
        Configuration conf = new Configuration();
//        conf.configure("hibernate.cfg.xml");
        Properties props = new Properties();
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost/epmdb");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "Admin@123");
        props.put(Environment.SHOW_SQL, "true");
        conf.setProperties(props);
        
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Activity.class);
        conf.addAnnotatedClass(UserRole.class);
        conf.addAnnotatedClass(Assistant.class);
        conf.addAnnotatedClass(Classes.class);
        conf.addAnnotatedClass(Comment.class);
        conf.addAnnotatedClass(Faculty.class);
        conf.addAnnotatedClass(JoinActivity.class);
        conf.addAnnotatedClass(Liked.class);
        conf.addAnnotatedClass(MissingReport.class);
        conf.addAnnotatedClass(Score.class);
        conf.addAnnotatedClass(ScoreStudent.class);
        conf.addAnnotatedClass(Semester.class);
        conf.addAnnotatedClass(Student.class);
        conf.addAnnotatedClass(Term.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        factory = conf.buildSessionFactory(registry);
    }
    
    public static SessionFactory getSessionFactory(){
        return factory;
    }
}
