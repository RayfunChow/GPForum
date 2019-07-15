package com.internship.gpforum.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "signinAction", method = RequestMethod.POST)
    public Boolean SignIn(HttpServletRequest request, ModelMap modelMap) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        System.out.println(email+"\t"+password);
        User user = userService.signIn(email, password);
        if (user != null) {
            request.getSession().setAttribute("User", user);
            modelMap.put("user", user);
            return true;
        } else {
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/signupAction", method = RequestMethod.POST)
    public String SignUp(HttpServletRequest request, ModelMap modelMap) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String veriCode = request.getParameter("veriCode");
        String code = new String();
        String msg = new String();
        code = redisService.get(email);
        if(code==null||code.equals("")){
            msg = "验证码失效";
            return msg;
        }
        if (!password.equals(confirmPassword)) {
            msg = "两次密码不一致";
            return msg;
        } else if (!code.equals(veriCode)) {
            msg = "验证码错误";
            return msg;
        }
//        if(password.equals(confirmPassword)){
        User user = new User();
        user.setUserEmail(email);
        user.setUserPassword(password);
        user.setNickName(email);
        user.setAvatar("/img/no_avatar.png");
        userService.signUp(user);
        request.getSession().setAttribute("User", user);
        modelMap.put("user", user);
        redisService.remove(email);
        msg = "注册成功";
        return msg;
//        }else
//            return "redirect:login";
    }

    @ResponseBody
    @RequestMapping(value = "sendCode", method = RequestMethod.POST)
    public String Send(HttpServletRequest request) {
        String userEmail = request.getParameter("email");
        if (!userService.checkRepeat(userEmail)) {
            return "该邮箱已被注册";
        } else {
            HtmlEmail email = new HtmlEmail();
            int n = userEmail.indexOf('@');
            String hostName = userEmail.substring(n + 1);
            email.setHostName("smtp." + hostName);
            email.setCharset("utf-8");
            try {
                email.addTo(userEmail);
                email.setFrom("1194688236@qq.com", "聚集地论坛");
                email.setAuthentication("1194688236@qq.com", "nlqqmczegpalbagd");
                email.setSubject("邮箱验证");//设置发送主题
                String code = RandomCode();
                email.setMsg("您正在注册聚集地论坛，您的验证码为： " + code + ",有效时间三分钟。 如非本人操作，请忽略本邮件。");//设置发送内容
                email.send();//进行发送
//            codeList.add(code);
//            redisUtils.set("codeList",json.toJSONString(codeList));
                redisService.set(userEmail, code);
                redisService.expire(userEmail, 300);
            } catch (EmailException e) {
                e.printStackTrace();
                return "发送失败，请重试";
            }
            return "验证码已发送";
        }
    }

    public String RandomCode() {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10) + "");
        }
        return code.toString();
    }

}
