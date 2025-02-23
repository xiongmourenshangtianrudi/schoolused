package com.schoolused.mapper;

import com.schoolused.entry.admin.adminControlinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface adminContrl {//管理员操作信息
    @Select("select controlid,useid,info,controlType from admincontrolinfo ")//查询所有的管理员操作
    public List<adminControlinfo> getAllControlInfo();

    @Select("select controlid,useid,info,controlType from admincontrolinfo where useid = #{id}")//查询某个管理员的操作行为
    public List<adminControlinfo>getControlInfobyUsedId(int id);


    @Insert("insert into admincontrolinfo(controlid,useid,info) values(${controlid},${useid},'${info}')")
    public int insertControlInfo(adminControlinfo info);//插入管理员操作权限


}
