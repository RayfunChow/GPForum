package com.internship.gpforum.dal.entity;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@ToString
@Table(name="user")
public class User {

    @Id
    private String userEmail;

    private String userPassword;

    private String nickName;

    private String avatar;

    private String location;

    private String hobby;

    private String birthday;

    private Integer userPostNumber;

    private String signature;

    private String permission;

    private String sex;

    @CreatedDate
    private Date regTime;

    private Date thisLogTime;

    private Date lastLogTime;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getThisLogTime() {
        return thisLogTime;
    }

    public void setThisLogTime(Date thisLogTime) {
        this.thisLogTime = thisLogTime;
    }

    public Date getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(Date lastLogTime) {
        this.lastLogTime = lastLogTime;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getUserPostNumber() {
        return userPostNumber;
    }

    public void setUserPostNumber(Integer userPostNumber) {
        this.userPostNumber = userPostNumber;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", location='" + location + '\'' +
                ", hobby='" + hobby + '\'' +
                ", birthday='" + birthday + '\'' +
                ", userPostNumber=" + userPostNumber +
                ", signature='" + signature + '\'' +
                ", permission='" + permission + '\'' +
                ", sex='" + sex + '\'' +
                ", regTime=" + regTime +
                ", thisLogTime=" + thisLogTime +
                ", lastLogTime=" + lastLogTime +
                '}';
    }
}
