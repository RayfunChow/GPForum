package com.internship.gpforum.dal.entity;


import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="moderator")
@ToString
public class Moderator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moderatorId;

    private String sectionName;

    private String moderatorEmail;

    private String moderatorNickName;

    private String moderatorAvatar;

    @CreatedDate
    private Date appointmentTime;

    public Integer getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(Integer moderatorId) {
        this.moderatorId = moderatorId;
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

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getModeratorNickName() {
        return moderatorNickName;
    }

    public void setModeratorNickName(String moderatorNickName) {
        this.moderatorNickName = moderatorNickName;
    }

    public String getModeratorAvatar() {
        return moderatorAvatar;
    }

    public void setModeratorAvatar(String moderatorAvatar) {
        this.moderatorAvatar = moderatorAvatar;
    }

    @Override
    public String toString() {
        return "Moderator{" +
                "moderatorId=" + moderatorId +
                ", sectionName='" + sectionName + '\'' +
                ", moderatorEmail='" + moderatorEmail + '\'' +
                ", moderatorNickName='" + moderatorNickName + '\'' +
                ", moderatorAvatar='" + moderatorAvatar + '\'' +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
