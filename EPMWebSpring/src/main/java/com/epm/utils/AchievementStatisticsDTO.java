/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

/**
 *
 * @author Win11
 */
public class AchievementStatisticsDTO {

    private String achievement;
    private int count;
    
    public AchievementStatisticsDTO(String achievement, int count){
        this.achievement = achievement;
        this.count = count;
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

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

}
