package com.internship.gpforum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.StarRepository;
import com.internship.gpforum.dal.entity.Star;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private StarRepository starRepository;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private JSONObject json = new JSONObject();

    @Override
    public void saveAll(List<Star> stars) {
        starRepository.saveAll(stars);
    }

    @Override
    public void confirmStar(String email, String id) {
        Star star = json.parseObject((String) redisTemplate.opsForHash().get(id + "_starRecords", email), Star.class);
        redisTemplate.opsForHash().delete(id + "_starRecords", email);
        starRepository.save(star);
    }

    @Override
    public void confirmReply(String userEmail, String replyEmail, String id) {
        redisTemplate.opsForHash().delete(userEmail + "_replied", replyEmail + id);
    }

    @Override
    public void confirmAllReply(String userEmail) {
        redisTemplate.delete(userEmail + "_replied");
    }

    @Override
    public void Star(User user, Integer id, String title, Integer starType) {
        if (!redisTemplate.opsForHash().hasKey(id + "_starRecords", user.getUserEmail())) {
            if(starType==1) {
                Star star = new Star();
                star.setPostId(id);
                star.setPostTitle(title);
                star.setUserEmail(user.getUserEmail());
                star.setUserNickName(user.getNickName());
                star.setStarTime(new Date());
                redisTemplate.opsForHash().put(id + "_starRecords", user.getUserEmail(), json.toJSONString(star));
            }else {
                starRepository.deleteByPostIdAndUserEmail(id,user.getUserEmail());
            }
        } else {
            redisTemplate.opsForHash().delete(id+"_starRecords", user.getUserEmail());
        }
        redisTemplate.opsForHash().increment("stars", id + "", starType);
        Double score=starType*0.8;
        redisTemplate.opsForZSet().incrementScore("scores",id+"",score);
    }
}
