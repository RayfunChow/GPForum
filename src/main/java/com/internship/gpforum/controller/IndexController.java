package com.internship.gpforum.controller;


import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.configure.OnlineUserList;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.PostService;
import com.internship.gpforum.service.RedisService;
import com.internship.gpforum.service.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Controller
public class IndexController {
    private JSONObject json = new JSONObject();

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping("/index")
    public String Start(HttpServletRequest request, ModelMap modelMap) {
        Set<Object> tops = redisTemplate.opsForZSet().reverseRange("scores", 0, 4);
        Iterator<Object> topArray = tops.iterator();
        List<Post> topPosts = new ArrayList<>();
        while (topArray.hasNext()) {
            Integer id = Integer.valueOf(topArray.next().toString());
            Post post = postService.getDetail(id);
            topPosts.add(post);
        }
        modelMap.put("topPosts",topPosts);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LoginController.COOKIE_NAME.equals(cookie.getName())) {
                    String email = cookie.getValue();
                    User user = userService.userCoookie(email);
                    if (user != null) {
                        if (OnlineUserList.containsKey(email)) {
                            HttpSession session = OnlineUserList.get(email);
                            session.invalidate();
                            OnlineUserList.remove(email);
                            HttpSession newSession = request.getSession();
                            newSession.setAttribute("User", user);
                            modelMap.put("User", user);
                            OnlineUserList.put(email, newSession);
                        } else {
                            request.getSession().setAttribute("User", user);
                            modelMap.put("User", user);
                            OnlineUserList.put(email, request.getSession());
                        }
                        return "index";
                    }
                }
            }
        }
        return "index";

    }

    @RequestMapping("/")
    public String toIndex() {

        return "forward:index";
    }
}
