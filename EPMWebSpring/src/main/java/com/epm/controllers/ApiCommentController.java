/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Comment;
import com.epm.pojo.User;
import com.epm.services.CommentService;
import com.epm.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/comments")
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Comment> createComment(@RequestParam int activityId, @RequestParam String content,
            @RequestPart(required = false) MultipartFile file, @RequestParam(required = false) Integer commentParentId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(auth.getName());
        if (commentParentId == null) {
            commentParentId = null;
        }
        if (file == null){
            file = null;
        }
        Comment comment = commentService.addComment(u.getId(), activityId, content, file, commentParentId);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/delete")
    @CrossOrigin
    public ResponseEntity<Void> deleteComment(@RequestParam int commentId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(auth.getName());
        boolean deleted = commentService.deleteComment(commentId, u.getId());
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activity")
    @CrossOrigin
    public ResponseEntity<List<Object[]>> getCommentsByActivity(@RequestParam int activityId) {
        List<Object[]> comments = commentService.getCommentsByActivity(activityId);
        return ResponseEntity.ok(comments);
    }

    
    @PostMapping(path = "/update", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateComment(@RequestParam int commentId, @RequestParam String content, @RequestPart(required = false) MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.getUserByUsername(auth.getName());
        
        if (file == null){
            file = null;
        }
        
        boolean update;
        try{
            update = commentService.updateComment(commentId, u.getId(), content, file);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(update, HttpStatus.OK);

    }
}
