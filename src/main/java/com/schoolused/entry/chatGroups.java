package com.schoolused.entry;

public class chatGroups {
    private RegularUsers user1;
    private RegularUsers user2;
    private int id;
    private int uid1;
    private int uid2;
    private chatContants chatContants;

    public RegularUsers getUser1() {
        return user1;
    }

    public void setUser1(RegularUsers user1) {
        this.user1 = user1;
    }

    public RegularUsers getUser2() {
        return user2;
    }

    public void setUser2(RegularUsers user2) {
        this.user2 = user2;
    }

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

    public chatContants getChatContants() {
        return chatContants;
    }

    public void setChatContants(chatContants chatContants) {
        this.chatContants = chatContants;
    }

    @Override
    public String toString() {
        return "chatGroups{" +
                "user1=" + user1 +
                ", user2=" + user2 +
                ", id=" + id +
                ", uid1=" + uid1 +
                ", uid2=" + uid2 +
                ", chatContants=" + chatContants +
                '}';
    }
}
