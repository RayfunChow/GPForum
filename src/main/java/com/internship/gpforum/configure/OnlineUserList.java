package com.internship.gpforum.configure;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class OnlineUserList {

    private static Map<String, HttpSession> onlineUsers =  new Hashtable<String, HttpSession>();

    private OnlineUserList() {
        super();
    }

    public static synchronized void put(String email, HttpSession session) {
        onlineUsers.put(email, session);
    }

    public static synchronized HttpSession get(String email) {
        return onlineUsers.get(email);
    }

    public static synchronized boolean containsKey(String email) {
        return onlineUsers.containsKey(email);
    }

    public static synchronized void remove(String email) {
        onlineUsers.remove(email);
    }
}


