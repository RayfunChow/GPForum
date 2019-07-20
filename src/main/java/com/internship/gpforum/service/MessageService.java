package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.Star;
import com.internship.gpforum.dal.entity.User;

import java.util.List;

public interface MessageService {
    void Star(User user, Integer id, String title, Integer starType);

    void saveAll(List<Star> stars);

    void confirmStar(String email,String id);

    void confirmReply(String userEmail,String replyEmail,String id);

    void confirmAllReply(String email);
}
