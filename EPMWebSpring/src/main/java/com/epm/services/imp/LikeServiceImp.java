/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.Liked;
import com.epm.pojo.User;
import com.epm.repositories.ActivityRepository;
import com.epm.repositories.LikeRepository;
import com.epm.repositories.UserRepository;
import com.epm.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service
public class LikeServiceImp implements LikeService {

    @Autowired
    private LikeRepository likedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public boolean likeActivity(int userId, int activityId) {
        User user = userRepository.findById(userId);
        Activity activity = activityRepository.findById(activityId);

        if (user == null || activity == null) {
            return false;
        }

        Liked liked = likedRepository.findByUserAndActivity(userId, activityId);
        if (liked == null) {
            liked = new Liked();
            liked.setUserId(user);
            liked.setActivityId(activity);
            liked.setActive(true);
            likedRepository.save(liked);
        } else {
            liked.setActive(!liked.getActive());
            likedRepository.update(liked);
        }

        return liked.getActive();
    }

    @Override
    public int getLikesCountByActivity(int activityId) {
        Activity activity = activityRepository.findById(activityId);
        if (activity == null) {
            return 0;
        }

        return (int) likedRepository.countByActivityId(activityId);
    }

    @Override
    public boolean existsByUserAndActivity(int userId, int activityId) {
        User user = userRepository.findById(userId);
        Activity activity = activityRepository.findById(activityId);

        if (user == null || activity == null) {
            return false;
        }

        return likedRepository.existsByUserIdAndActivityId(userId, activityId);
    }
}
