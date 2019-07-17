package com.internship.gpforum.service.impl;

import com.internship.gpforum.dal.SectionRepository;
import com.internship.gpforum.dal.entity.Section;
import com.internship.gpforum.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section sectionDetail(String sectionName) {
        return sectionRepository.findBySectionName(sectionName);
    }
}
