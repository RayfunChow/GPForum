package com.internship.gpforum.dal.entity;


import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="report")
@ToString
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    private String reportReason;

    private String reportType;

    @CreatedDate
    private Date reportTime;

    private String reportedLink;

    private String reviewer;

    private Date reviewTime;

    private String reviewReason;

    private boolean reviewResult;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportedLink() {
        return reportedLink;
    }

    public void setReportedLink(String reportedLink) {
        this.reportedLink = reportedLink;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public boolean isReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(boolean reviewResult) {
        this.reviewResult = reviewResult;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportReason='" + reportReason + '\'' +
                ", reportType='" + reportType + '\'' +
                ", reportTime=" + reportTime +
                ", reportedLink='" + reportedLink + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", reviewTime=" + reviewTime +
                ", reviewReason='" + reviewReason + '\'' +
                ", reviewResult=" + reviewResult +
                '}';
    }
}
