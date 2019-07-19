package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    User findByUserEmailAndUserPassword(String email,String password);

    User findByUserEmail(String email);

    @Query(value = "select * from user where nick_name like %?1%",nativeQuery = true)
    List<User> findByNickName(String keyword);

}
