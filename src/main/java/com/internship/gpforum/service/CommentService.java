package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CommentService {

    List<Comment> findAllParentComment(Integer postId);

    List<Comment> findAllChildComment(Integer parentCommentId,Integer postId);

    List<Comment> findMyComments(String email);

    void insert(Comment comment);
}
