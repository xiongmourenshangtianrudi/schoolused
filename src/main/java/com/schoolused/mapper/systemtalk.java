package com.schoolused.mapper;

import com.schoolused.entry.admin.talkToUserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface systemtalk {
    @Select("select * from talktouser where id=${id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "time",property = "time"),
            @Result(column = "id",property = "msg",javaType = String.class,one = @One(select = "com.schoolused.mapper.systemtalk.getmsg"))
    })
    public talkToUserInfo getallTalkInfo(int id);


    @Select("select msg from talktousermsg where id=${id}")
    public String getmsg(int id);//查询详细信息

    @Select("select id,time,title from talktouser limit ${page},#{size}")//分页获取公告
    public List<talkToUserInfo> getAllTalk(Map<String,Object> map);


    @Select("select count(*) from talktouser")
    public long getTotal();

    @Select("select max(id) from talktouser")
    public long getMaxid();

    @Insert("insert into talktouser(id,title,time) values (${id},'${title}',now())")
    public int newtalktitle(int id,String title);

    @Insert("insert into talktousermsg(id,msg) values (${id},'${msg}')")
    public int newtalkmsg(int id,String msg);
}
