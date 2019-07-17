package com.internship.gpforum.controller;

import com.internship.gpforum.configure.OnlineUserList;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.RedisService;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("User");
        String email = user.getUserEmail();
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        if (user.getUserPassword().equals(password)) {
            if (newPassword.equals(confirmPassword)) {
                Cookie cookie = new Cookie(LoginController.COOKIE_NAME, "");
//                redisService.remove(email + LoginController.COOKIE_NAME);
                OnlineUserList.remove(email);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                request.getSession().removeAttribute("User");
                user.setUserPassword(newPassword);
                userService.update(user);
                return "密码修改成功";
            } else {
                return "两次密码不一致";
            }
        } else {
            return "密码错误";
        }
    }


    @ResponseBody
    @RequestMapping(value = "updateProfileAction",method = RequestMethod.POST)
    public String updateProfile(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("User");
        String location=request.getParameter("location");
        String nickName=request.getParameter("userNickname");
        String signature=request.getParameter("userSignature");
        String hobby=request.getParameter("hobby");
        String sex=request.getParameter("sex");
        String birthday=request.getParameter("birthday");
        user.setNickName(nickName);
        user.setBirthday(birthday);
        user.setHobby(hobby);
        user.setLocation(location);
        user.setSignature(signature);
        user.setSex(sex);
        userService.update(user);
        return "修改信息成功";
    }
}