package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeratorRepository extends JpaRepository<Moderator,Integer> {

    List<Moderator> findBySectionName(String sectionName);
}
