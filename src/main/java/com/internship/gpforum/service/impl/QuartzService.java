package com.internship.gpforum.service.impl;

import com.internship.gpforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class QuartzService {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private PostService postService;

    private Map<Object,Object> starsMap;

    private Map<Object,Object> browseMap;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void readCache(){
         starsMap=redisTemplate.opsForHash().entries("stars");
         browseMap=redisTemplate.opsForHash().entries("browseNumber");
    }

    @Scheduled(cron = "0/1 * * * * ? ")
    public void timerToNow(){
        Map<Object,Object> starsmap = redisTemplate.opsForHash().entries("stars");
        Map<Object,Object> browsemap=redisTemplate.opsForHash().entries("browseNumber");
        if(!starsmap.equals(starsMap)) {
            starsMap=starsmap;
            String id;
            String stars;
            Integer Id;
            Integer Stars;
            for (Map.Entry<Object, Object> vo : starsmap.entrySet()) {
                id = String.valueOf(vo.getKey());
                stars = String.valueOf(vo.getValue());
                Id = Integer.valueOf(id);
                Stars = Integer.valueOf(stars);
                postService.update(Id, Stars,0);
            }
        }
        if(!browsemap.equals(browseMap)){
            browseMap=browsemap;
            String id;
            String browse;
            Integer Id;
            Integer browseNumber;
            for (Map.Entry<Object, Object> vo : browsemap.entrySet()) {
                id = String.valueOf(vo.getKey());
                browse = String.valueOf(vo.getValue());
                Id = Integer.valueOf(id);
                browseNumber = Integer.valueOf(browse);
                postService.update(Id, browseNumber,1);
            }
        }
    }
}
