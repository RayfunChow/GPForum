package com.internship.gpforum.dal.entity;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="section")
@ToString
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sectionId;

    private String sectionName;

    private String description;

    private String sectionPostNumber;

    private String sectionStatus;

    @CreatedDate
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSectionPostNumber() {
        return sectionPostNumber;
    }

    public void setSectionPostNumber(String sectionPostNumber) {
        this.sectionPostNumber = sectionPostNumber;
    }

    public String getSectionStatus() {
        return sectionStatus;
    }

    public void setSectionStatus(String sectionStatus) {
        this.sectionStatus = sectionStatus;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", description='" + description + '\'' +
                ", sectionPostNumber='" + sectionPostNumber + '\'' +
                ", sectionStatus='" + sectionStatus + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
