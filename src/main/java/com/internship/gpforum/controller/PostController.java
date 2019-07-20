package com.internship.gpforum.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.entity.Comment;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private MessageService messageService;

    private JSONObject json = new JSONObject();

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @RequestMapping("postDetail")
    public String toPostDetail(ModelMap modelMap, Integer postId, HttpServletRequest request) {
        User userInfo = (User) request.getSession().getAttribute("User");
        modelMap.put("User", userInfo);
        Post postDetail = postService.getDetail(postId);
        if(postDetail==null){
            redisTemplate.opsForHash().delete(userInfo.getUserEmail()+"_records","/postDetail?postDetail="+postId);
            return "notfound";
        }
        modelMap.put("postDetail", postDetail);
        userService.addBrowseRecord(userInfo.getUserEmail(), postId, postDetail.getTitle());
        List<Comment> parentComments = commentService.findAllParentComment(postId);
        User user;
        for (int i = 0; i < parentComments.size(); i++) {
            user = userService.userCoookie(parentComments.get(i).getUserEmail());
            parentComments.get(i).setUserNickName(user.getNickName());
        }
        modelMap.put("parentCommentsNumber", parentComments.size());
        if (parentComments.size() != 0) {
            modelMap.put("parentComments", parentComments);
        }
        return "postDetail";
    }

    @RequestMapping(value = "writeComment", method = RequestMethod.POST)
    @ResponseBody
    public String writeComment(HttpServletRequest request, ModelMap modelMap) {
        User user = (User) request.getSession().getAttribute("User");
        modelMap.put("User", user);
        String userEmail = user.getUserEmail();
        String nickName = user.getNickName();
        String content = request.getParameter("commentContent");                     //评论内容
        if (!BaiduAPI.content_adult(content).equals("0")) {
            return "内容涉及敏感词，请重试！";
        }
        Comment comment = new Comment();
        Integer postId = Integer.parseInt(request.getParameter("postId"));
        comment.setPostId(postId);
        String parentId=request.getParameter("parentCommentId");
        String respondentEmail=request.getParameter("respondentEmail");
        String respondentNickname=request.getParameter("respondentNickname");
        if ( parentId!= null) {
            Integer parentCommentId=Integer.parseInt(parentId);
            comment.setParentCommentId(parentCommentId);
        }
        comment.setRespondentUserNickName(respondentNickname);
        comment.setRespondentUserEmail(respondentEmail);
        comment.setUserEmail(userEmail);
        comment.setCommentTime(new Date());
        comment.setContent(content);
        comment.setUserNickName(nickName);
        commentService.insert(comment);
        redisTemplate.opsForHash().put(respondentEmail+"_replied",user.getUserEmail()+comment.getCommentId(),json.toJSONString(comment));
        return "发表成功";
    }

    @ResponseBody
    @RequestMapping("commentDetail")
    public String commentDetail(ModelMap modelMap, HttpServletRequest request) {
        User user0 = (User) request.getSession().getAttribute("User");
        modelMap.put("User", user0);
        Integer parentId = Integer.valueOf(request.getParameter("parentCommentId"));
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        List<Comment> childrenComments = commentService.findAllChildComment(parentId, postId);
        User user;
        for (int i = 0; i < childrenComments.size(); i++) {
            user = userService.userCoookie(childrenComments.get(i).getUserEmail());
            childrenComments.get(i).setUserNickName(user.getNickName());
            user = userService.userCoookie(childrenComments.get(i).getRespondentUserEmail());
            childrenComments.get(i).setRespondentUserNickName(user.getNickName());
        }
//        if (childrenComments.size() != 0) {
//            modelMap.put("childrenComments", childrenComments);
//            modelMap.put("childrenCommentsNumber", childrenComments.size());
//        }
        return JSON.toJSONString(childrenComments);
    }

    @ResponseBody
    @RequestMapping(value = "writeAction", method = RequestMethod.POST)
    public String write(HttpServletRequest request) {
        try {
            String section_name = request.getParameter("section_name");       //板块
            String title = request.getParameter("title");                                        //标题
            String content = request.getParameter("content");                                    //内容
            Boolean invisible = Boolean.parseBoolean(request.getParameter("invisible"));        //是否可评论
            String summary = content.replaceAll("<([^>]*)>", "");                //摘要需要先把内容正则化，然后再次判断其长度;

            if (BaiduAPI.image_audit(content).equals("不合规")) {                                        //图片审核
                return JSON.toJSONString("图片不合规，请重试！");
            } else {
                if (summary.length() >= 20)                                                                // 取摘要
                    summary = summary.substring(0, 20);
                User user = (User) request.getSession().getAttribute("User");
                String author_email = user.getUserEmail();
                String author_nickname = user.getNickName();
                //敏感词
                if (!BaiduAPI.content_adult(title).equals("0") || !BaiduAPI.content_adult(content.replaceAll("<([^>]*)>", "")).equals("0")) {
                    return JSON.toJSONString("内容涉及敏感词，请重试！");
                } else {
                    postService.writeContent(author_email, author_nickname, section_name, title, summary, content, invisible, "正常", new Date());
                    return JSON.toJSONString("发表成功！");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return JSON.toJSONString("发表失败！");
        }

    }

    @ResponseBody
    @RequestMapping(value = "getPostContent", method = RequestMethod.POST)
    public String getPostContent(HttpServletRequest request) {

        String postId = request.getParameter("postId");

        return postService.getDetail(Integer.parseInt(postId)).getContent();
    }

    @ResponseBody
    @RequestMapping(value = "star",method = RequestMethod.POST)
    public boolean Star(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("User");
        String postTitle=request.getParameter("postTitle");
        Integer postId=Integer.valueOf(request.getParameter("postId"));
        Integer starType=Integer.valueOf(request.getParameter("starType"));
        messageService.Star(user,postId,postTitle,starType);
        return true;
    }
}
