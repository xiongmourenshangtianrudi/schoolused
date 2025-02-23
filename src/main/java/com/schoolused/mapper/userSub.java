package com.schoolused.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.schoolused.entry.userSubEntry;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface userSub {


    @Insert("insert into(userid,message,state) usersub values(${userid},${message},${state})")
    public int newMessage(userSubEntry userSub);

    @Select("Selset id,userid,message,state from usersub")
    public List<userSubEntry> getSubMessage();
}
