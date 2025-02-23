package com.schoolused.mapper;

import com.schoolused.entry.RegularUsers;
import com.schoolused.entry.commentContent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface comment {

    @Select("select CommentID,com_id,user_id,content,dianzan,caidi,createTime from comment where com_id = ${com_id} order by createTime Desc ")

    @Results({
            @Result(column = "CommentID",property = "CommentID"),
            @Result(column = "com_id",property = "com_id"),
            @Result(column = "content",property = "content"),
            @Result(column = "dianzan",property = "dianzan"),
            @Result(column = "caidi",property = "caidi"),
            @Result(column = "createTime",property = "createTime"),
            @Result(column="user_id", property="regularUsers", javaType= RegularUsers.class,
                    one=@One(select = "com.schoolused.mapper.RegularUsersDao.findUsersById"))

    })


    public List<commentContent> getCommentByCom_id(int com_id);

    @Insert("insert into comment(com_id,user_id,content,dianzan,caidi,createTime) values(${com_id},${user_id},'${content}',0,0,now())")
    public int newCommentText(commentContent commtent);

    @Delete("delete from comment where CommentID = ${CommentID}")
    public int deleteComment(int CommentID);//删除评论
}
