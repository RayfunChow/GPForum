package com.internship.gpforum.service.impl;

import com.internship.gpforum.dal.UserRepository;
import com.internship.gpforum.dal.entity.User;
import com.internship.gpforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signIn(String email, String password) {
        User user = userRepository.findByUserEmailAndUserPassword(email, password);
        return user;
    }

    @Override
    public void signUp(User user) {
        userRepository.save(user);
    }


    @Override
    public boolean checkRepeat(String email) {   //检查是否重复注册
        User user = userRepository.findByUserEmail(email);
        if (user == null) {                         //可以注册返回true
            return true;
        } else                                   //否则返回false
            return false;
    }

    @Override
    public User userCoookie(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findByNickName(String keyword) {
        return userRepository.findByNickName(keyword);
    }
}
