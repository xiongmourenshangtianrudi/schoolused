package com.schoolused.mapper;

import com.schoolused.entry.RegularUsers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RegularUsersDao {//用户层操作,已经被弃用
    @Insert("insert into usertable(userName,userPwd,sex,birthday) values('${userName}','${userPwd}','${sex}','${birthday}')")
    public int enroll(RegularUsers regularUsers);//用户注册

    @Update("update usertable set userimage = '${imgurl}' where userid=${id}")
    public int changeavator(int id,String imgurl);//更新用户头像

    @Update("update usertable set showName='${showName}',sex='${sex}',birthday='${birthday}' where userName='${userName}'")
    public int changeUserInfo(RegularUsers regularUsers);//更新用户信息
    @Select("select * from usertable where userName = '${userName}'")
    public RegularUsers findUsersByName(RegularUsers regularUsers);//通过用户账户名获取用户信息

    @Select("select userid,showName,userName,sex,birthday,userimage,state from usertable where userid = '${id}'")
    public RegularUsers findUsersById(int id);//通过用户账户名获取用户信息

    @Select("select userid,showName,userName,sex,birthday,userimage,state from usertable ")
    public List<RegularUsers> findAlluserInfo();//获取所有的用户信息

    @Select("select * from usertable where userName = #{userName}")
    public RegularUsers findUsersByuserName(String userName);//通过用户账户名获取用户信息

    @Select("select count(*) from usertable where userName ='${userName}'")
    long checkUserRepeat(RegularUsers regularUsers);


    @Update("update usertable set state =0 where userid=#{userid}")
    public int makeuserban(int userid);

    @Update("update usertable set state =1 where userid=#{userid}")
    public int makeuserfree(int userid);




}
