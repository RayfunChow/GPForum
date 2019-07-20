package com.internship.gpforum.service.impl;

import com.internship.gpforum.dal.CommentRepository;
import com.internship.gpforum.dal.entity.Comment;
import com.internship.gpforum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAllParentComment(Integer postId) {
        return commentRepository.findAllParentComment(postId);
    }

    @Override
    public List<Comment> findAllChildComment(Integer parentCommentId, Integer postId) {
        return commentRepository.findByParentCommentIdAndPostIdOrderByCommentTime(parentCommentId,postId);
    }

    @Override
    public Page<Comment> findMyComments(String email,PageRequest pageRequest) {
        Page<Comment> commentList=commentRepository.findByRespondentUserEmailOrderByCommentTimeDesc(email,pageRequest);
        return commentList;
    }

    @Override
    public void insert(Comment comment) {
        commentRepository.save(comment);
    }
}
