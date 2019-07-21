package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CommentRepository  extends JpaRepository<Comment,Integer> {

    @Query(value = "select * from comment where post_id=?1 and parent_comment_id is null order by comment_time",nativeQuery = true)
    List<Comment> findAllParentComment(Integer postId);

    List<Comment> findByParentCommentIdAndPostIdOrderByCommentTime(Integer parentCommentId,Integer postId);

    @Transactional
    void deleteAllByPostId(Integer postId);

    Page<Comment> findByRespondentUserEmailOrderByCommentTimeDesc(String email,Pageable pageable);

    List<Comment> findByUserEmail(String email);
}
