package com.internship.gpforum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.UserRepository;
import com.internship.gpforum.dal.entity.BrowseRecord;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    private JSONObject json = new JSONObject();

    @Override
    public User signIn(String email, String password) {
        User user;
        try {
            String result=String.valueOf(redisTemplate.opsForHash().get("userList",email));
            user= json.parseObject(result,User.class);
            if(user!=null) {
                return user;
            }
        }catch (Exception e) {
           e.printStackTrace();
        }
        user = userRepository.findByUserEmailAndUserPassword(email, password);
        return user;
    }

    @Override
    public void signUp(User user) {
        redisTemplate.opsForHash().put("userList",user.getUserEmail(),json.toJSONString(user));
        userRepository.save(user);
    }


    @Override
    public boolean checkRepeat(String email) {   //检查是否重复注册

        User user;
        try {
            boolean result=redisTemplate.opsForHash().hasKey("userList",email);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        user = userRepository.findByUserEmail(email);
        if(user==null){                         //可以注册返回true
            return false;
        }else                                   //否则返回false
            return true;
   }

    @Override
    public User userCoookie(String email) {
        User user=new User();
        try {
            String result=String.valueOf(redisTemplate.opsForHash().get("userList",email));
            user= json.parseObject(result,User.class);
            if(user!=null) {
                return user;
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        user = userRepository.findByUserEmail(email);
        return user;
    }

    @Override
    public void update(User user) {
        redisTemplate.opsForHash().delete("userList",user.getUserEmail());
        redisTemplate.opsForHash().put("userList",user.getUserEmail(),json.toJSONString(user));
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findByNickName(String keyword) {
        return userRepository.findByNickName(keyword);
    }

    @Override
    public User findByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    public void addBrowseRecord(String email, Integer id, String title) {
        BrowseRecord browseRecord=new BrowseRecord();
        String browseUrl="/postDetail?postId="+id;
        browseRecord.setPostTitle(title);
        browseRecord.setBrowseUrl(browseUrl);
        browseRecord.setBrowseTime(new Date());
        if(redisTemplate.opsForHash().hasKey(email+"_records",browseUrl)) {
           redisTemplate.opsForHash().delete(email+"_records",browseUrl);
        }
        if(!redisTemplate.opsForHash().hasKey(email+"_records",browseUrl)) {
            redisTemplate.opsForHash().increment("browseNumber", id + "", 1);
            Double score=0.2;
            redisTemplate.opsForZSet().incrementScore("scores",id+"",score);
        }
        redisTemplate.opsForHash().put(email+"_records",browseUrl,json.toJSONString(browseRecord));
    }
}
