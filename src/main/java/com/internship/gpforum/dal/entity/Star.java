package com.internship.gpforum.dal.entity;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Table(name="star")
public class Star {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer starId;
    
    private Integer postId;
    
    private String userEmail;

    @CreatedDate
    private Date starTime;

    public Integer getstarId() {
        return starId;
    }

    public void setstarId(Integer starId) {
        this.starId = starId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getstarTime() {
        return starTime;
    }

    public void setstarTime(Date starTime) {
        this.starTime = starTime;
    }

    @Override
    public String toString() {
        return "star{" +
                "starId=" + starId +
                ", postId=" + postId +
                ", userEmail='" + userEmail + '\'' +
                ", starTime='" + starTime + '\'' +
                '}';
    }
}
