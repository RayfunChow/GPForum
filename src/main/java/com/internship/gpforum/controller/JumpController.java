package com.internship.gpforum.controller;

import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JumpController {

    @Autowired
    private UserService userService;



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

}
