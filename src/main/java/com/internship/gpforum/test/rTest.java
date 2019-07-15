//package com.internship.gpforum.test;
//
//import com.alibaba.fastjson.JSONObject;
//import com.internship.gpforum.dal.entity.User;
//import com.internship.gpforum.service.RedisService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class rTest {
//    //使用json字符串进行交互，引入fastjson的JSONObject类
//    private JSONObject json = new JSONObject();
//    //	@Autowired注解把redisService注入进来
//    @Autowired
//    private RedisService redisService;
//
//    @Test
//    public void contextLoads() throws Exception {
//    }
//
//
//    /**
//     * 插入字符串
//     */
//    @Test
//    public void setString() {
//        redisService.set("redis_string_test", "springboot redis test");
//    }
//
//    /**
//     * 获取字符串
//     */
//    @Test
//    public void getString() {
//        String result = redisService.get("redis_string_test");
//        System.out.println(result);
//    }
//
//    /**
//     * 插入对象
//     */
//    @Test
//    public void setObject() {
//        User person = new User();
//        person.setNickName("user");
//        redisService.set("redis_obj_test", json.toJSONString(person));
//    }
//
//    /**
//     * 获取对象
//     */
//    @Test
//    public void getObject() {
//        String result = redisService.get("redis_obj_test");
//        User person = json.parseObject(result, User.class);
//        System.out.println(json.toJSONString(person));
//    }
//
//    /**
//     * 插入对象List
//     */
//    @Test
//    public void setList() {
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
//        redisService.set("redis_list_test", json.toJSONString(list));
//    }
//
//    /**
//     * 获取list
//     */
//    @Test
//    public void getList() {
//        String result = redisService.get("redis_list_test");
//        List<String> list = json.parseArray(result, String.class);
//        System.out.println(list);
//    }
//
//    @Test
//    public void remove() {
//        redisService.remove("redis_test");
//    }
//
//}
