/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epm.pojo.Activity;
import com.epm.pojo.Comment;
import com.epm.pojo.User;
import com.epm.repositories.ActivityRepository;
import com.epm.repositories.CommentRepository;
import com.epm.repositories.UserRepository;
import com.epm.services.CommentService;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Comment addComment(int userId, int activityId, String content, MultipartFile file, Integer commentParentId) {
        User user = userRepository.findById(userId);
        Activity activity = activityRepository.findById(activityId);

        if (user == null || activity == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setUserId(user);
        comment.setActivityId(activity);
        comment.setContent(content);

        if (file != null && !file.isEmpty()) {
            comment.setFile(file);

            if (!comment.getFile().isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(comment.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    comment.setImage(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(CommentServiceImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        if (commentParentId != null) {
            Comment parentComment = commentRepository.findById(commentParentId);
            parentComment.setIsParent(true);
            updateIsParent(parentComment);
            comment.setCommentParent(parentComment);
        }

        commentRepository.save(comment);

        return comment;
    }

    @Override
    public List<Comment> getCommentsByActivity(int activityId) {
        return this.commentRepository.findByActivityId(activityId);
    }

    @Override
    public boolean deleteComment(int commentId, int userId) {
        Comment comment = commentRepository.findById(commentId);

        if (comment == null || !comment.getUserId().getId().equals(userId)) {
            return false;
        }

        commentRepository.delete(comment);
        return true;
    }

    @Override
    public boolean updateComment(int commentId, int userId, String content, MultipartFile file) {
        Comment comment = commentRepository.findById(commentId);
        if (comment == null) {
            throw new RuntimeException("Comment not found");
        }

        if (comment.getUserId().getId() != userId) {
            throw new RuntimeException("User not authorized to edit this comment");
        }
 
        comment.setContent(content);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        if (file != null && !file.isEmpty()) {
            comment.setFile(file);
            if (!comment.getFile().isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(comment.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    comment.setImage(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(CommentServiceImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        commentRepository.update(comment);

        return true;
    }

    @Override
    public List<Comment> getComments(Activity activity) {
        return new ArrayList<>(activity.getCommentSet());
    }

    @Override
    public void updateIsParent(Comment comment) {
        this.commentRepository.updateIsParent(comment);
    }

    @Override
    public List<Comment> getCommentsChild(int commentParentId) {
        return this.commentRepository.getCommentsChild(commentParentId);
    }
}
