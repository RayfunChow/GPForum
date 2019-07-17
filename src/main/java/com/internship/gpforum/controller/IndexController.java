package com.internship.gpforum.controller;


import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.configure.OnlineUserList;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.RedisService;
import com.internship.gpforum.service.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {
    private JSONObject json = new JSONObject();

    @Autowired
    private RedisService redisUtils;

    @Autowired
    private UserService userService;

//    private List<String> codeList=new ArrayList<>();

    @RequestMapping( "/")
    public String Start(HttpServletRequest request, ModelMap modelMap) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LoginController.COOKIE_NAME.equals(cookie.getName())) {
                    String email=cookie.getValue();
                    User user=userService.userCoookie(email);
//                    String password=redisUtils.get(email+LoginController.COOKIE_NAME);
                    if(user!=null){
//                        if (user.getUserPassword().equals(password)) {
                            if (OnlineUserList.containsKey(email)) {
                                HttpSession session = OnlineUserList.get(email);
                                session.invalidate();
                                OnlineUserList.remove(email);
                                HttpSession newSession = request.getSession();
                                newSession.setAttribute("User", user);
                                modelMap.put("User",user);
                                OnlineUserList.put(email, newSession);
                                return "index";
                            } else {
                                request.getSession().setAttribute("User", user);
                                modelMap.put("User",user);
                                OnlineUserList.put(email, request.getSession());
                                return "index";
                            }
//                        } else {
//                            return "login";
//                        }
                    }else {
                        return "login";
                    }
                }
            }
        }
        return "login";

    }

    @RequestMapping("/index")
    public String toIndex(ModelMap modelMap, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        return "index";
    }
}
