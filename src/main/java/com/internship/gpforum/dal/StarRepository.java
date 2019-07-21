package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface StarRepository extends JpaRepository<Star,Integer> {

    Star findByPostIdAndUserEmail(Integer id,String email);

    @Transactional
    void deleteByPostIdAndUserEmail(Integer id,String email);
}
