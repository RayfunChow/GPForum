package com.internship.gpforum.dal.entity;


import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attention")
@ToString
public class Attention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attentionId;

    private String concernedUserEmail;

    private String userEmail;

    @CreatedDate
    private Date attentionTime;

    private String concernedUserNickName;

    public Integer getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(Integer attentionId) {
        this.attentionId = attentionId;
    }

    public String getConcernedUserEmail() {
        return concernedUserEmail;
    }

    public void setConcernedUserEmail(String concernedUserEmail) {
        this.concernedUserEmail = concernedUserEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(Date attentionTime) {
        this.attentionTime = attentionTime;
    }

    public String getConcernedUserNickName() {
        return concernedUserNickName;
    }

    public void setConcernedUserNickName(String concernedUserNickName) {
        this.concernedUserNickName = concernedUserNickName;
    }

    @Override
    public String toString() {
        return "Attention{" +
                "attentionId=" + attentionId +
                ", concernedUserEmail='" + concernedUserEmail + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", attentionTime=" + attentionTime +
                ", concernedUserNickName='" + concernedUserNickName + '\'' +
                '}';
    }
}
