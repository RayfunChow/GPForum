package com.internship.gpforum.service.impl;

import com.internship.gpforum.dal.ModeratorRepository;
import com.internship.gpforum.dal.entity.Moderator;
import com.internship.gpforum.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeratorServiceImpl implements ModeratorService {

    @Autowired
    private ModeratorRepository moderatorRepository;

    @Override
    public List<Moderator> findModerators(String sectionName) {
        return moderatorRepository.findBySectionName(sectionName);
    }
}
