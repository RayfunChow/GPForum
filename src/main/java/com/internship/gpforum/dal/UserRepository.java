package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User findByUserEmailAndUserPassword(String email,String password);

    User findByUserEmail(String email);



}
