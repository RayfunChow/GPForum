//package com.internship.gpforum.configure;
//
//import ch.qos.logback.core.util.ContextUtil;
//import com.internship.gpforum.dal.entity.User;
//import com.internship.gpforum.service.RedisService;
//import com.internship.gpforum.service.impl.RedisServiceImpl;
//import org.hibernate.Session;
//
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionAttributeListener;
//import javax.servlet.http.HttpSessionBindingEvent;
//import javax.servlet.http.HttpSessionListener;
//import java.util.HashMap;
//import java.util.Map;
//
//public class LoginListenner implements HttpSessionListener, HttpSessionAttributeListener {
//
////    public static ArrayList<String> LimitedSessionIds=new ArrayList<>();
//
//    /**
//     * 用于存放账号和session对应关系的map
//     */
//    private RedisServiceImpl redisService;
//
//    /**
//     * 当向session中放入数据触发
//     *
//     * @param event
//     */
//    public void attributeAdded(HttpSessionBindingEvent event) {
//        String name = event.getName();
//        //用户已登录
//        if (name.equals("loginuser")) {
//            User user = (User) event.getValue();
//            String email = user.getUserEmail();
//            HttpSession session = event.getSession();
//            if (OnlineUserList.containsKey(email)) {
//                HttpSession ss = OnlineUserList.get(email);
//                //将session置为失效
//                ss.invalidate();
//            }
//            OnlineUserList.put(email, session);
//        }
//
//    }
//
//
//    /**
//     * 当向session中移除数据触发
//     */
//    public void attributeRemoved(HttpSessionBindingEvent event) {
//        String name = event.getName();
//
//        if (name.equals("loginuser")) {
//            User user = (User) event.getValue();
//            OnlineUserList.remove(user.getUserEmail());
//
//        }
//    }
//
//    public void attributeReplaced(HttpSessionBindingEvent event) {
//
//    }
//
////    public Map<String, HttpSession> getMap() {
////        return map;
////    }
////
////    public void setMap(Map<String, HttpSession> map) {
////        this.map = map;
////    }
//
//}
