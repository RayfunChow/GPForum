package com.internship.gpforum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.PostRepository;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.Star;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    private JSONObject json = new JSONObject();

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

    @Override
    public void Star(User user, Integer id,String title, Integer starType) {
            if (!redisTemplate.opsForHash().hasKey(id+"_starRecords",user.getUserEmail())) {
            Star star = new Star();
            star.setPostId(id);
            star.setPostTitle(title);
            star.setUserEmail(user.getUserEmail());
            star.setUserNickName(user.getNickName());
            star.setStarTime(new Date());
            redisTemplate.opsForHash().put(id+"_starRecords",user.getUserEmail(),json.toJSONString(star));
            }else {
                redisTemplate.opsForHash().delete("starRecords", id + "", user.getUserEmail());
            }
            redisTemplate.opsForHash().increment("stars",id+"",starType);
    }

    @Override
    public void update(Integer id, Integer number,Integer type) {
        Post post=postRepository.findByPostId(id);
        if(type==0) {
            post.setStarNumber(number);
        }else if(type==1)
            post.setBrowseNumber(number);
        postRepository.saveAndFlush(post);
    }


    public void writeContent(String author_email,String authorNickname, String section_name, String title, String summary, String content, boolean invisible, String post_status, Date lastEditTime) {
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
        post.setStarNumber(0);
        post.setAuthorNickName(authorNickname);
        postRepository.save(post);
        Integer id=post.getPostId();
        redisTemplate.opsForHash().put("stars",id,0);
        redisTemplate.opsForHash().put("browseNumber",id,0);
    }

}
