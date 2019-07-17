package com.internship.gpforum.service.impl;

import com.internship.gpforum.dal.PostRepository;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    public Page<Post> getByEdiTime(String sectionName,PageRequest pageRequest){
        Page<Post> postList=postRepository.findBySectionNameAndInvisibleOrderByLastEditTimeDesc(sectionName,pageRequest,false);
        return postList;
    }

    @Override
    public Page<Post> getByStarNumber(String sectionName, PageRequest pageRequest) {
        Page<Post> postList=postRepository.findBySectionNameAndInvisibleOrderByStarNumberDesc(sectionName,pageRequest,false);
        return postList;
    }

    @Override
    public Post getDetail(Integer postId) {
        return postRepository.findByPostId(postId);
    }

    @Override
    public Page<Post> getHisPost(String userEmail, PageRequest pageRequest) {
        return postRepository.findByAuthorEmailOrderByLastEditTimeDesc(userEmail,pageRequest);
    }


    public void writeContent(String author_email, String section_name, String title, String summary, String content, boolean invisible, String post_status, Date lastEditTime) {
        Post post=new Post();
        post.setAuthorEmail(author_email);
        post.setSectionName(section_name);
        post.setTitle(title);
        post.setSummary(summary);
        post.setContent(content);
        post.setFirstImg(content);
        post.setInvisible(invisible);
        post.setPostStatus(post_status);
        post.setLastEditTime(lastEditTime);
        postRepository.save(post);
    }
}
