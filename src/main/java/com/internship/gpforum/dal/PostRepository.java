package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,String> {

    Page<Post> findBySectionNameAndCommentableOrderByLastEditTimeDesc(String sectionName, Pageable request,boolean commentable);

    Page<Post> findBySectionNameAndCommentableOrderByStarNumberDesc(String sectionName,Pageable request,boolean commentable);

    Post findByPostId(Integer id);

    Page<Post> findByAuthorEmailOrderByLastEditTimeDesc(String authorEmail,Pageable request);
}
