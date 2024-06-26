/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.CommentResponse;
import com.epm.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class CommentMapper {
    @Autowired
    private UserMapper userMapper;

    public CommentResponse toCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setCreatedDate(comment.getCreatedDate());
        commentResponse.setImage(comment.getImage());
        commentResponse.setIsParent(comment.getIsParent());
        commentResponse.setUserResponse(this.userMapper.toUserResponse(comment.getUserId()));
        return commentResponse;
    }
}
