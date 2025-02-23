package com.schoolused.mapper;

import com.schoolused.entry.RegularUsers;
import com.schoolused.entry.chatContants;
import com.schoolused.entry.chatGroups;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface chatMapper {
    @Select("select * from chatgroup" +" "+
            "where uid1=${userid} or uid2=${userid}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "uid1",property = "uid1"),
            @Result(column = "uid2",property = "uid2"),
            @Result(column="uid1", property="user1", javaType= RegularUsers.class,
                    one=@One(select="com.schoolused.mapper.RegularUsersDao.findUsersById")),
            @Result(column="uid2", property="user2", javaType= RegularUsers.class,
                    one=@One(select="com.schoolused.mapper.RegularUsersDao.findUsersById")),
            @Result(column = "id",property = "chatContants",javaType = chatContants.class,
                    one = @One(select = "findAllChatcontant1"))
    })
    public List<chatGroups> findAllChatGroups(int userid);//通过用户id获取到用户对应的聊天记录列表

    @Select("select count(*) from chatgroup" +" "+
            "where uid1=${uid1} and uid2=${uid2} or uid1=${uid2} and uid2=${uid1}")
    public int isFirstChat(chatGroups chatGroups);

    @Select("select * from chatcontant where cid = ${cid} ORDER BY createtime desc limit 1,1")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "uid1",property = "uid1"),
            @Result(column = "uid2",property = "uid2"),
            @Result(column = "cid",property = "cid"),
            @Result(column = "content",property = "content"),
            @Result(column = "createtime",property = "createtime"),

    })
    public chatContants findAllChatcontant1(int cid);//通过聊天组信息获取聊天记录

    @Select("select * from chatcontant where cid = ${cid} ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "uid1",property = "uid1"),
            @Result(column = "uid2",property = "uid2"),
            @Result(column = "cid",property = "cid"),
            @Result(column = "content",property = "content"),
            @Result(column = "createtime",property = "createtime"),

    })
    public List<chatContants> findAllChatcontant(int cid);//通过聊天组信息获取聊天记录
//    @Insert("")


@Insert("insert into chatcontant(uid1,uid2,cid,content,createtime) values(#{uid1},#{uid2},#{cid},#{content},#{createtime})")
public int addChatInfo(chatContants chatContants);


@Insert("insert into chatgroup(uid1,uid2) values(#{uid1},#{uid2})")
public int addNewChat(chatGroups chatGroups);
}