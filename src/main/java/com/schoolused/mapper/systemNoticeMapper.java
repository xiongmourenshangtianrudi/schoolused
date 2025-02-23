package com.schoolused.mapper;

import com.schoolused.entry.admin.systemNotice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface systemNoticeMapper {

    @Select("select id,adminid,userid,info,title,createtime from systemnotice where userid = #{userid}")//查询用户的系统通知
    public List<systemNotice> getSystemNotices(int userid);


    @Insert("insert into systemnotice(adminid,userid,info,title,createtime) values(${adminid},${userid},'${info}','${title}',now())")
    public int insertinfo(systemNotice systemNotice);

}
