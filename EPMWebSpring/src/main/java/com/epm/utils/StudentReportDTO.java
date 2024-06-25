/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.Score;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.pojo.User;

/**
 *
 * @author Win11
 */
public class StudentReportDTO {

    private Student student;
    private User user;
    private Semester semester;
    private Activity activity;
    private Score score;
    private Term term;
    private Classes classes;
    private String achievement;

    public StudentReportDTO(Student student, Semester semester, Activity activity, Score score, Term term, Classes classes, User user) {
        this.student = student;
        this.semester = semester;
        this.activity = activity;
        this.score = score;
        this.term = term;
        this.classes = classes;
        this.user = user;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the semester
     */
    public Semester getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    /**
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return the score
     */
    public Score getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * @return the term
     */
    public Term getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(Term term) {
        this.term = term;
    }

    /**
     * @return the classes
     */
    public Classes getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    /**
     * @return the achievement
     */
    public String getAchievement() {
        return achievement;
    }

    /**
     * @param achievement the achievement to set
     */
    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }
}
