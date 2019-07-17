package com.internship.gpforum.dal;

import com.internship.gpforum.dal.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {

    @Query(value = "select * from section",nativeQuery = true)
    List<Section> findAll();

    Section findBySectionName(String sectionName);
}
