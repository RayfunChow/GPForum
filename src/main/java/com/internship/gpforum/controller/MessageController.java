package com.internship.gpforum.controller;

import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.entity.*;
import com.internship.gpforum.service.CommentService;
import com.internship.gpforum.service.MessageService;
import com.internship.gpforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    private JSONObject json = new JSONObject();

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @RequestMapping("message")
    public String toMessage(HttpServletRequest request, ModelMap modelMap, @RequestParam(required = false, defaultValue = "1") Integer pageIndex, @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        User user=(User)request.getSession().getAttribute("User");
        modelMap.put("User",user);

        List<Post> postList=postService.getPosts(user.getUserEmail());
        List<Star> recentStars=new ArrayList<>();
        for(Post post:postList){
            if(redisTemplate.hasKey(post.getPostId()+"_starRecords")){
                Map<Object,Object> map=redisTemplate.opsForHash().entries(post.getPostId()+"_starRecords");
                for(Map.Entry<Object, Object> vo :map.entrySet()){
                    Star star=json.parseObject((String) vo.getValue(),Star.class);
                    recentStars.add(star);
                }
            }
        }
        modelMap.put("recentStars",recentStars);

        List<BrowseRecord> browseRecords=new ArrayList<>();
        if(redisTemplate.hasKey(user.getUserEmail()+"_records")){
            Map<Object,Object> map=redisTemplate.opsForHash().entries(user.getUserEmail()+"_records");
            for(Map.Entry<Object, Object> vo :map.entrySet()){
                BrowseRecord browseRecord=json.parseObject((String) vo.getValue(),BrowseRecord.class);
                browseRecords.add(browseRecord);
            }
        }
        modelMap.put("browseRecords",browseRecords);

        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize);


        Map<Object,Object> map =redisTemplate.opsForHash().entries(user.getUserEmail()+"_replied");
        List<Comment> commentRecords=new ArrayList<>();
        Comment comment;
        for(Map.Entry<Object,Object> oe:map.entrySet()){
            comment=json.parseObject((String) oe.getValue(),Comment.class);
            commentRecords.add(comment);
        }
        modelMap.put("commentRecords",commentRecords);

        return "message";
    }

    @ResponseBody
    @RequestMapping(value = "confirmStar",method = RequestMethod.POST)
    public boolean confirmStar(HttpServletRequest request){
        String email=request.getParameter("email");
        String id=request.getParameter("id");
        messageService.confirmStar(email,id);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "confirmAllStars",method = RequestMethod.POST)
    public boolean confirmAllStars(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("User");
        List<Post> postList = postService.getPosts(user.getUserEmail());
        List<Star> recentStars = new ArrayList<>();
        for (Post post : postList) {
            if (redisTemplate.hasKey(post.getPostId() + "_starRecords")) {
                Map<Object, Object> map = redisTemplate.opsForHash().entries(post.getPostId() + "_starRecords");
                for (Map.Entry<Object, Object> vo : map.entrySet()) {
                    Star star = json.parseObject((String) vo.getValue(), Star.class);
                    recentStars.add(star);
                }
                redisTemplate.delete(post.getPostId() + "_starRecords");
            }
        }
        messageService.saveAll(recentStars);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "confirmReply",method = RequestMethod.POST)
    public boolean confirmReply(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("User");
        String replyEmail=request.getParameter("replyEmail");
        String id=request.getParameter("commentId");
        messageService.confirmReply(user.getUserEmail(),replyEmail,id);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "confirmAllReply",method = RequestMethod.POST)
    public boolean confirmAllReply(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("User");
        messageService.confirmAllReply(user.getUserEmail());
        return true;
    }
}
