package com.internship.gpforum.controller;

import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.entity.BrowseRecord;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.Star;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.PostService;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class JumpController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    private JSONObject json = new JSONObject();

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @RequestMapping("updateProfile")
    public String toUpdateProfile(ModelMap modelMap, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        return "updateProfile";
    }

    @RequestMapping("write")
    public String toWrite(HttpServletRequest request,ModelMap modelMap,String sectionName){
        User user = (User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        modelMap.put("sectionName",sectionName);
        return "write";
    }

    @RequestMapping("notfound")
    public String toNotfound(HttpServletRequest request,ModelMap modelMap,String sectionName){
        User user = (User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        modelMap.put("sectionName",sectionName);
        return "notfound";
    }



}
