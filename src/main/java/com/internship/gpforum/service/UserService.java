package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.User;

import java.util.List;

public interface UserService {

    User signIn(String email,String password);

    void signUp(User user);

    boolean checkRepeat(String email);

    User userCoookie(String email);

    void update(User user);


    List<User> findByNickName(String keyword);

    void addBrowseRecord(String email,Integer id,String title);

}
