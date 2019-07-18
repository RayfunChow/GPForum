package com.internship.gpforum.dal.entity;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="browseRecord")
@ToString
public class BrowseRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    private String browseUrl;

    private String postTitle;

    @CreatedDate
    private Date browseTime;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getBrowseUrl() {
        return browseUrl;
    }

    public void setBrowseUrl(String browseUrl) {
        this.browseUrl = browseUrl;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    @Override
    public String toString() {
        return "BrowseRecord{" +
                "recordId=" + recordId +
                ", postId=" + browseUrl +
                ", postTitle='" + postTitle + '\'' +
                ", browseTime=" + browseTime +
                '}';
    }
}
