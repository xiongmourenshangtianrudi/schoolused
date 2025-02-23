package com.schoolused.entry;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Timer;

public class User {

    private int userid;

    private String userName;

    private String userPwd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String userbirthday;

    private String userPermissions;



    public void setUserbirthday(String userbirthday) {
        this.userbirthday = userbirthday;
    }


    public String getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(String userPermissions) {
        this.userPermissions = userPermissions;
    }

    public int getUserid() {
        return userid;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserbirthday() {
        return userbirthday;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserbirthday() {
        this.userbirthday = userbirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userbirthday='" + userbirthday + '\'' +
                ", userPermissions=" + userPermissions +
                '}';
    }
}

