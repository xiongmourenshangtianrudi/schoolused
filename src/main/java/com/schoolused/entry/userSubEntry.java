package com.schoolused.entry;

public class userSubEntry {
    private int id;
    private int userid;
    private String info;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return info;
    }

    public void setMessage(String info) {
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "userSub{" +
                "id=" + id +
                ", userid=" + userid +
                ", message='" + info + '\'' +
                ", state=" + state +
                '}';
    }
}
