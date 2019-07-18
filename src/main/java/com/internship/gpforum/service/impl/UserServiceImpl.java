package com.internship.gpforum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.UserRepository;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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
        }
       catch (Exception e) {
            user = userRepository.findByUserEmailAndUserPassword(email, password);
        }
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
            user = userRepository.findByUserEmail(email);
            if(user==null){                         //可以注册返回true
                return false;
            }else                                   //否则返回false
                return true;
        }
    }

    @Override
    public User userCoookie(String email) {
        User user;
        try {
            String result=String.valueOf(redisTemplate.opsForHash().get("userList",email));
            user= json.parseObject(result,User.class);
        }catch (Exception e){
            user=userRepository.findByUserEmail(email);
        }
        return user;
    }

    @Override
    public void update(User user) {
        redisTemplate.opsForHash().delete("userList",user.getUserEmail());
        redisTemplate.opsForHash().put("userList",user.getUserEmail(),json.toJSONString(user));
        userRepository.saveAndFlush(user);
    }
}
