package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findBySectionNameOrderByLastEditTimeDesc(String sectionName);

    List<Post> findBySectionNameOrderByStarNumberDesc(String sectionName);

    Post findByPostId(Integer id);
}
