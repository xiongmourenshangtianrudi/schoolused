package com.schoolused.entry.admin;

public class systemNotice {

    private int id;
    private int adminid;
    private int userid;
    private String info;
    private String title;
    private String createtime;

    public systemNotice() {

    }

    public systemNotice(int id, int adminid, int userid, String info, String title, String createtime) {
        this.id = id;
        this.adminid = adminid;
        this.userid = userid;
        this.info = info;
        this.title = title;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "systemNotice{" +
                "id=" + id +
                ", adminid=" + adminid +
                ", userid=" + userid +
                ", info='" + info + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
