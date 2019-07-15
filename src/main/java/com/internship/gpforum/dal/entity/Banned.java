package com.internship.gpforum.dal.entity;


import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="banned")
@ToString
public class Banned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bannedId;

    private Integer sectionId;

    private String sectionName;

    private String moderatorEmail;

    private String reason;

    private Date endTime;

    public Integer getBannedId() {
        return bannedId;
    }

    public void setBannedId(Integer bannedId) {
        this.bannedId = bannedId;
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

    public String getModeratorEmail() {
        return moderatorEmail;
    }

    public void setModeratorEmail(String moderatorEmail) {
        this.moderatorEmail = moderatorEmail;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Banned{" +
                "bannedId=" + bannedId +
                ", sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", moderatorEmail='" + moderatorEmail + '\'' +
                ", reason='" + reason + '\'' +
                ", endTime=" + endTime +
                '}';
    }
}
