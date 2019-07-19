package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.User;

public interface UserService {

    User signIn(String email,String password);

    void signUp(User user);

    boolean checkRepeat(String email);

    User userCoookie(String email);

    void update(User user);

    void addBrowseRecord(String email,Integer id,String title);
}
