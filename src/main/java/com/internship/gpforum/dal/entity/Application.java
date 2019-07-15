package com.internship.gpforum.dal.entity;


import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="application")
@ToString
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;

    private String applicantEmail;

    private String applicationReason;

    private String applicationContent;

    @CreatedDate
    private Date applicationTime;

    private String reviewerEmail;

    private String reviewReason;

    private Date reviewTime;

    private String sectionName;

    private String sectionIntroduction;

    private boolean reviewResult;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicationReason() {
        return applicationReason;
    }

    public void setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason;
    }

    public String getApplicationContent() {
        return applicationContent;
    }

    public void setApplicationContent(String applicationContent) {
        this.applicationContent = applicationContent;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionIntroduction() {
        return sectionIntroduction;
    }

    public void setSectionIntroduction(String sectionIntroduction) {
        this.sectionIntroduction = sectionIntroduction;
    }

    public boolean isReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(boolean reviewResult) {
        this.reviewResult = reviewResult;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", applicantEmail='" + applicantEmail + '\'' +
                ", applicationReason='" + applicationReason + '\'' +
                ", applicationContent='" + applicationContent + '\'' +
                ", applicationTime=" + applicationTime +
                ", reviewerEmail='" + reviewerEmail + '\'' +
                ", reviewReason='" + reviewReason + '\'' +
                ", reviewTime=" + reviewTime +
                ", sectionName='" + sectionName + '\'' +
                ", sectionIntroduction='" + sectionIntroduction + '\'' +
                ", reviewResult=" + reviewResult +
                '}';
    }
}
