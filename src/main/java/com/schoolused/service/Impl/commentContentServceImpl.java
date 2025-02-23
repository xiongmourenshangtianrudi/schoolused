package com.schoolused.service.Impl;

import com.schoolused.entry.commentContent;
import com.schoolused.mapper.comment;
import com.schoolused.service.commentContentServce;
import com.schoolused.until.logInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentContentServceImpl implements commentContentServce {

    @Autowired
    comment comment;
    @Override
    public List<commentContent> getCommentByCom_id(int com_id) { //获取指定商品的评论信息
        List<commentContent> comments = null;
        if(com_id !=0 ){
             comments = comment.getCommentByCom_id(com_id);
        }else{
            logInfo.log(10,"上传的商品id有问题");
        }

        return comments;
    }

    @Override
    public int newComent(commentContent commentss) { //新增一条评论
        int x =0;
        if(commentss !=null){
            x =comment.newCommentText(commentss);
        }
        if(x ==1){
            return 301;
        }else{
            return 300;
        }
    }

    @Override
    public int deleteComment(int CommentID) {  //删除一条评论
        int x =0;
        if(CommentID !=0){
            x = comment.deleteComment(CommentID);

        }
        return x;
    }
}
