package com.schoolused.entry;

import java.util.List;

public class RegularUsers {
    private int userid;
    private String userName;
    private String userPwd;
    private String sex;
    private String birthday;
    private String userimage;
    private String showName;
    private int state;
    private List<commodity> comoditys;//用户正在出售的商品
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public List<commodity> getComoditys() {
        return comoditys;
    }

    public void setComoditys(List<commodity> comoditys) {
        this.comoditys = comoditys;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RegularUsers{" +
                "userid=" + userid +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", userimage='" + userimage + '\'' +
                '}';
    }
}
