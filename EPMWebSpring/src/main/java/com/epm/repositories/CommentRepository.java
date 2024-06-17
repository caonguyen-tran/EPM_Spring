/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.repositories;

import com.epm.pojo.Comment;
import java.util.List;

/**
 *
 * @author Win11
 */
public interface CommentRepository {
    List<Object[]> findByActivityId(int activityId);
    List<Comment> findByUserId(int userId);
    void save(Comment c);
    void update(Comment c);
    Comment findById(int commentParentId);
    void delete(Comment c);
}
