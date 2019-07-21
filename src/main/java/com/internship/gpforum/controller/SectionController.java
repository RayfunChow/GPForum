package com.internship.gpforum.controller;

import com.internship.gpforum.dal.entity.Moderator;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.Section;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.ModeratorService;
import com.internship.gpforum.service.PostService;
import com.internship.gpforum.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private PostService postService;

    @Autowired
    private ModeratorService moderatorService;

    @RequestMapping("section")
    public String toSection(HttpServletRequest request,ModelMap modelMap){
        User user = (User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        List<Section> sections=sectionService.findAll();
        modelMap.put("sections",sections);
        return "section";
    }

    @RequestMapping("sectionDetail")
    public String toSectionDetail(HttpServletRequest request, ModelMap modelMap, String sectionName,@RequestParam(required = false, defaultValue = "lastEditTime")String sortType, @RequestParam(required = false, defaultValue = "1") Integer pageIndex, @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        User user = (User)request.getSession().getAttribute("User");
        modelMap.put("User",user);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize,new Sort(Sort.Direction.DESC,sortType));
        Page<Post> postPage=postService.getSectionDetail(sectionName,pageRequest);
        modelMap.put("postPage",postPage);
        Section sectionDetail=sectionService.sectionDetail(sectionName);
        modelMap.put("sectionDetail",sectionDetail);
        List<Moderator> moderators=moderatorService.findModerators(sectionName);
        modelMap.put("moderators",moderators);
        return "sectionDetail";
    }
}
