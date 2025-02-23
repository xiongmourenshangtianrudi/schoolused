package com.schoolused.entry;

public class chatContants {
    private int id;
    private int uid1;
    private int uid2;
    private int cid;
    private String content;
    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid1() {
        return uid1;
    }

    public void setUid1(int uid1) {
        this.uid1 = uid1;
    }

    public int getUid2() {
        return uid2;
    }

    public void setUid2(int uid2) {
        this.uid2 = uid2;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }



    @Override
    public String toString() {
        return "chatContants{" +
                "id=" + id +
                ", uid1=" + uid1 +
                ", uid2=" + uid2 +
                ", cid=" + cid +
                ", content='" + content + '\'' +
                ", createtime='" + createtime + '\'' +

                '}';
    }
}
