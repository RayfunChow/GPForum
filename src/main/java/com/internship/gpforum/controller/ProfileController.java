package com.internship.gpforum.controller;

import com.internship.gpforum.common.PasswordEncryption;
import com.internship.gpforum.configure.OnlineUserList;
import com.internship.gpforum.configure.UploadPhotoResult;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.BaiduAPI;
import com.internship.gpforum.service.PostService;
import com.internship.gpforum.service.RedisService;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ProfileController {

    //    @Value("${static.upload.path}")
    private String uploadPath = "D:/img/";
    //    @Value("${static.server.address}")
    private String staticServerAddr = "http://127.0.0.1:8777/";


    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PostService postService;

    @RequestMapping("profile")
    public String toProfile(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String userEmail, @RequestParam(required = false, defaultValue = "1") Integer pageIndex, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        User user = (User) request.getSession().getAttribute("User");
        if(user==null&&userEmail==null){
            return "login";
        }
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize);
        if (userEmail == null || userEmail == user.getUserEmail()) {
            Page<Post> postPage = postService.getHisPost(user.getUserEmail(), pageRequest);
//            System.out.println(postPage.getTotalPages());
            modelMap.put("postPage", postPage);
//            modelMap.put("postPageSize",postPage.getTotalPages());
            modelMap.put("User", user);
            modelMap.put("targetUser", user);
        }else{
            User other=userService.userCoookie(userEmail);
            Page<Post> postPage=postService.getHisPost(userEmail,pageRequest);
            modelMap.put("postPage",postPage);
            modelMap.put("User", user);
            modelMap.put("targetUser", other);
        }
        return "profile";
    }

    @ResponseBody
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("User");
        String email = user.getUserEmail();
        String password = PasswordEncryption.encryption_SHA_256( request.getParameter("password"));
        String newPassword = PasswordEncryption.encryption_SHA_256(request.getParameter("newPassword"));
        String confirmPassword = PasswordEncryption.encryption_SHA_256(request.getParameter("confirmPassword"));
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
    @RequestMapping(value = "updateProfileAction", method = RequestMethod.POST)
    public String updateProfile(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("User");
        String location = request.getParameter("location");
        String nickName = request.getParameter("userNickname");
        String signature = request.getParameter("userSignature");
        String hobby = request.getParameter("hobby");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        if(BaiduAPI.content_adult(nickName).equals("0")) {
            user.setNickName(nickName);
            user.setBirthday(birthday);
            user.setHobby(hobby);
            user.setLocation(location);
            if (BaiduAPI.content_adult(signature).equals("0")) {
                user.setSignature(signature);
            }else{
                return "内含敏感信息，修改失败";
            }
            user.setSex(sex);
            userService.update(user);
            return "修改信息成功";
        }else{
            return "内含敏感信息，修改失败";
        }
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public UploadPhotoResult upload(MultipartFile file, HttpServletRequest request) {
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return new UploadPhotoResult(false, null, "上传出错，请稍后再试");
        }
        User user = (User) request.getSession().getAttribute("User");
        user.setAvatar(staticServerAddr + fileName);
        userService.update(user);
        return new UploadPhotoResult(true, staticServerAddr + fileName, null);
    }
    @RequestMapping(value = "checkupload", method = RequestMethod.POST)
    @ResponseBody
    public String checkupload(HttpServletRequest request) {
        String imgurl = request.getParameter("dataURL");

        if (BaiduAPI.image(imgurl).equals("合规")) {
            return "图片合规";
        } else {
            return "图片不合规";
        }
    }
}
