package com.internship.gpforum.controller;


import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    private JSONObject json = new JSONObject();

    @Autowired
    private RedisService redisUtils;

//    private List<String> codeList=new ArrayList<>();

    @RequestMapping("/")
    public String Start(){
        return "login";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

//    @RequestMapping("star")
//    public String Star(){
//        redisUtils.set("1","点赞");
//        User person1 = new User();
//        User person2 = new User();
//        User person3 = new User();
//        person1.setNickName("1");
//        person2.setNickName("2");
//        person3.setNickName("3");
//        List<User> list = new ArrayList<>();
//        list.add(person1);
//        list.add(person2);
//        list.add(person3);
//        redisUtils.set("redis_list_test", json.toJSONString(list));
//        return "index";
//    }

//    @RequestMapping("send")
//    public String Send(String userEmail){
//        HtmlEmail email=new HtmlEmail();
//        int n=userEmail.indexOf('@');
//        String hostName=userEmail.substring(n+1);
//        email.setHostName("smtp."+hostName);
//        email.setCharset("utf-8");
//        try {
//            email.addTo(userEmail);
//            email.setFrom("1194688236@qq.com","聚集地论坛");
//            email.setAuthentication("1194688236@qq.com","nlqqmczegpalbagd");
//            email.setSubject("邮箱验证");//设置发送主题
//            String code=RandomCode();
//            email.setMsg("您正在注册聚集地论坛，您的验证码为： "+code+"。 如非本人操作，请忽略本邮件。");//设置发送内容
//            email.send();//进行发送
////            codeList.add(code);
////            redisUtils.set("codeList",json.toJSONString(codeList));
//            redisUtils.set(userEmail,code);
//            redisUtils.expire(userEmail,300);
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }
//        return "index";
//    }
//
//    public String RandomCode(){
//        StringBuffer code=new StringBuffer();
//        Random random=new Random();
//        for(int i=0;i<6;i++){
//            code.append(random.nextInt(10)+"");
//        }
//        return code.toString();
//    }
}
