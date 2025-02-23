package com.schoolused.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface tokenDao {

    @Insert("insert into token values ('${key}','${value}');")
    public int saveToken(String key,String value);

    @Delete("Delete from token where value ='${value}")
    public int deleteToken(String value);
}
