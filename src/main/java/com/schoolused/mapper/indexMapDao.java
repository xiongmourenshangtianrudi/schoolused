package com.schoolused.mapper;

import com.schoolused.entry.indexMapUrl;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface indexMapDao {
    @Select("select id,url,adurl from indexmap")
    public List<indexMapUrl> getMapUrl();//获取所有的主页轮播图

    @Select("select id from indexmap")
    public List<Integer> getMapId();//查询表中所有的url的id
    @Insert("insert into indexmap(id,url,adurl) values(#{id},#{url},#{adurl})")
    public int AddedMap(indexMapUrl indexMapUrl);


    @Delete("<script>delete from indexmap where id in<foreach " +
            "collection='ids' item='item' " +
            "open='(' separator=',' close=')'>#{item} </foreach> </script>")//批量删除轮播图
    public int deleturl(@Param("ids") List<Integer> ids);
}
