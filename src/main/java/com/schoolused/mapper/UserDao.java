package com.schoolused.mapper;

import com.schoolused.entry.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserDao {
    @Select("select * from userInfo")
    public List<User> findAlluser();

    @Select("Select * from userInfo where userName='${username}'")
    public User login(String username);

    @Insert("insert into userinfo value(${userid},'${userName}','${userPwd}','${userbirthday}','${userPermissions}')")
    public int insertUerInfo(User user);

    @Delete("<script>delete from userinfo where userid in " +
            "<foreach collection='ids' item='param' open='(' separator=',' close=')'> " +
            "#{param}" +
            "</foreach></script>")//用foreach需要在最外层包裹一个script
    public int delete(@Param("ids") List<Integer> ids);//根据id批量删除用户

    @Delete("delete from userinfo where userid =${userid}")
    public int deleteUser(int userid);

    @Update("update userinfo set userName = '${userName}',userPwd='${userPwd}',userbirthday='${userbirthday}',userPermissions='${userPermissions}' where userid = ${userid}")
    public int updateUserInfo(User user);
}
