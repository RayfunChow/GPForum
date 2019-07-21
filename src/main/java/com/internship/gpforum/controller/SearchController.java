package com.internship.gpforum.controller;

import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.Section;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.PostService;
import com.internship.gpforum.service.SectionService;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    SectionService sectionService;


    @RequestMapping("searchAction")
    public String searchAction(ModelMap modelMap, String keyword,HttpServletRequest request) {
//        String keyword = request.getParameter("keyword");
        User user=(User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        List<User> users = userService.findByNickName(keyword);
        List<Section> section = sectionService.findSections(keyword);
        List<Post> posts = postService.findInTitleAndContent(keyword);
        modelMap.put("users", users);
        modelMap.put("sections", section);
        modelMap.put("posts", posts);
        modelMap.put("keyword", keyword);
        return "searchResult";
    }

    @ResponseBody
    @RequestMapping("getVeryHotWord")
    public String getVeryHotWord(){
        return postService.getHotWords();
    }



}
