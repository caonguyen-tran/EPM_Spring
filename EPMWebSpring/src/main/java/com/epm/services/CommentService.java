/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services;

import com.epm.pojo.Comment;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Win11
 */
public interface CommentService {

    Comment addComment(int userId, int activityId, String content, MultipartFile file, Integer commentParentId);

    List<Object[]> getCommentsByActivity(int activityId);

    boolean deleteComment(int commentId, int userId);

    boolean updateComment(int commentId, int userId, String content, MultipartFile file);
}
