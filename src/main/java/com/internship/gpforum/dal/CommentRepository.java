package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository  extends JpaRepository<Comment,Integer> {

    @Query(value = "select * from gpf_dev.comment where post_id=?1 and parent_comment_id is null order by comment_time",nativeQuery = true)
    List<Comment> findAllParentComment(Integer postId);

    List<Comment> findByParentCommentIdAndPostIdOrderByCommentTime(Integer parentCommentId,Integer postId);

    @Query(value = "select top 20 comment.user_email as user_email,comment.user_nick_name as user_nick_name,comment.content as content,post.post_id as post_id,post.title as post_title" +
            " from gpf_dev.comment,gpf_dev.post where post.author_email=?2 and comment.post_id=post.post_id and comment.parent_comment_id is null order by comment_time",nativeQuery = true)
    List<Comment> findMyComments(String email);
}
