package com.internship.gpforum.controller;

import com.internship.gpforum.dal.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JumpController {

    @RequestMapping("profile")
    public String toProfile(){
        return "profile";
    }

    @RequestMapping("updateProfile")
    public String toUpdateProfile(ModelMap modelMap, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        return "updateProfile";
    }

    @RequestMapping("write")
    public String toWrite(){
        return "write";
    }

    @RequestMapping("section")
    public String toSection(){
        return "section";
    }

}
