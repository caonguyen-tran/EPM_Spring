/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.User;
import com.epm.services.LikeService;
import com.epm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/likes")
public class ApiLikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    @PostMapping("/like")
    @CrossOrigin
    public ResponseEntity<Boolean> likeActivity(@RequestParam int activityId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(auth.getName());
        boolean liked;
        try {
            liked = likeService.likeActivity(u.getId(), activityId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(liked, HttpStatus.OK);
    }

    @GetMapping("/count")
    @CrossOrigin
    public ResponseEntity<Integer> getLikesCountByActivity(@RequestParam int activityId) {
        int count = likeService.getLikesCountByActivity(activityId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/exists")
    @CrossOrigin
    public ResponseEntity<Boolean> existsByUserIdAndActivityId(@RequestParam int activityId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(auth.getName());
        boolean exists = likeService.existsByUserAndActivity(u.getId(), activityId);
        return ResponseEntity.ok(exists);
    }
}
