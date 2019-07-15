package com.internship.gpforum.dal.entity;


import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Table(name="collection")
@ToString
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;

    private Integer postId;

    private String postTitle;

    private String userEmail;

    @CreatedDate
    private String collectionTime;

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", collectionTime='" + collectionTime + '\'' +
                '}';
    }
}
