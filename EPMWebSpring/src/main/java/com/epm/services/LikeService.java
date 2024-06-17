/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

/**
 *
 * @author Win11
 */
public interface LikeService {
    boolean likeActivity(int userId, int activityId);
    int getLikesCountByActivity(int activityId);
    boolean existsByUserAndActivity(int userId, int activityId);
}
