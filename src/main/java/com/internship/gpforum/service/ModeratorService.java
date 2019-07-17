package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.Moderator;

import java.util.List;

public interface ModeratorService {

    List<Moderator> findModerators(String sectionName);
}
