package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllParentComment(Integer postId);

    List<Comment> findAllChildComment(Integer parentCommentId,Integer postId);

    void insert(Comment comment);
}
