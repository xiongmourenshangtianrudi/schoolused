package com.schoolused.entry;

public class commentContent { //商品评论实体
    private int CommentID;//评论id
    private int com_id;//商品id
    private int user_id;//评论者id
    private String content;//评论信息
    private int dianzan;//点赞数
    private int caidi;//恶评数
    private String createTime;
    private RegularUsers regularUsers;

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDianzan() {
        return dianzan;
    }

    public void setDianzan(int dianzan) {
        this.dianzan = dianzan;
    }

    public int getCaidi() {
        return caidi;
    }

    public void setCaidi(int caidi) {
        this.caidi = caidi;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public RegularUsers getRegularUsers() {
        return regularUsers;
    }

    public void setRegularUsers(RegularUsers regularUsers) {
        this.regularUsers = regularUsers;
    }

    @Override
    public String toString() {
        return "commentContent{" +
                "CommentID=" + CommentID +
                ", com_id=" + com_id +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", dianzan=" + dianzan +
                ", caidi=" + caidi +
                '}';
    }
}
