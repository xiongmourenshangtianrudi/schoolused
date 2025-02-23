package com.schoolused.entry;

public class userFund {//这是金额管理的实体类
    private int userid;
    private float money;
    private String state;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "userFund{" +
                "userid=" + userid +
                ", money=" + money +
                ", state='" + state + '\'' +
                '}';
    }
}
