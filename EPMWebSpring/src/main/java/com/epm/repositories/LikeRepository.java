/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.Liked;

/**
 *
 * @author Win11
 */
public interface LikeRepository {

    Liked findByUserAndActivity(int userId, int activityId);

    long countByActivityId(int activityId);

    boolean existsByUserIdAndActivityId(int userId, int activityId);
    
    void save(Liked like);
    
    void update(Liked like);
}
