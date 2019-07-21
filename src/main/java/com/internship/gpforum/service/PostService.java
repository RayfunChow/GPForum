package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface PostService {
    void writeContent(String author_email, String author_nickname, String section_name, String title, String summary, String content, boolean invisible, String post_status, Date lastEditTime);

    Page<Post> getSectionDetail(String sectionName, PageRequest pageRequest);

    Post getDetail(Integer postId);

    Page<Post> getHisPost(String userEmail, PageRequest pageRequest);

    Page<Post> getMyPost(String userEmail, PageRequest pageRequest);

    List<Post> getPosts(String email);

    String getHotWords();

    List<Post> findInTitleAndContent(String keyword);

    void deleteByPostId(Integer postId);

    void update(Integer id,Integer number,Integer type);

    boolean isStared(Integer id,String email);

}
