package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.Star;
import com.internship.gpforum.dal.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PostService {
    void writeContent(String author_email,String authorNickname, String section_name, String title, String summary, String content, boolean invisible, String post_status, Date lastEditTime);

    Page<Post> getByEdiTime(String sectionName, PageRequest pageRequest);

    Page<Post> getByStarNumber(String sectionName, PageRequest pageRequest);

    Post getDetail(Integer postId);

    Page<Post> getHisPost(String userEmail, PageRequest pageRequest);

    List<Post> getPosts(String email);

    String getHotWords();

    List<Post> findInTitleAndContent(String keyword);

    void update(Integer id,Integer number,Integer type);




}
