package com.internship.gpforum.service;

import com.internship.gpforum.dal.entity.Section;

import java.util.List;

public interface SectionService {

    List<Section> findAll();

    Section sectionDetail(String sectionName);

    Section findBySectionName(String sectionName);

    List<Section> findSections(String keyword);
}
