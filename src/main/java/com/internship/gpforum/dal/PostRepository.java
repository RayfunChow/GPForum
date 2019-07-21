package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    Page<Post> findBySectionNameAndInvisible(String sectionName, Pageable request, boolean invisible);

//    Page<Post> findBySectionNameAndInvisibleOrderByStarNumberDesc(String sectionName, Pageable request, boolean invisible);

    Post findByPostId(Integer id);

    Page<Post> findByAuthorEmailOrderByLastEditTimeDesc(String email,Pageable pageable);

    Page<Post> findByAuthorEmailAndInvisibleOrderByLastEditTimeDesc(String authorEmail, boolean invisible,Pageable request);

    @Query(value = "select * from post where title like %?1% or content like %?1%", nativeQuery = true)
    List<Post> findInTitleAndContent(String keyword);


    @Transactional
    void deleteByPostId(Integer postId);

    List<Post> findByAuthorEmail(String email);

}
