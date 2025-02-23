package com.schoolused.controler;


import com.schoolused.entry.commentContent;
import com.schoolused.service.Impl.commentContentServceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/comment")
public class commentControl {

    @Autowired
    commentContentServceImpl comContent;


    @GetMapping("/getCommet/{com_id}")
    public Result getComment(@PathVariable int com_id){
        List<commentContent> res =null;
        if(com_id !=0){
            res = comContent.getCommentByCom_id(com_id);
            return new Result(301,res,"获取成功");
        }else{
            return new Result(300,"获取失败");
        }


    }

    @PostMapping("/newComment")
    public Result newComment(@RequestBody commentContent comCotent){

        int x = comContent.newComent(comCotent);

        if(x ==1){
            return new Result(301,"评论成功");
        }else{
            return new Result(300,"评论失败");
        }
    }

    @GetMapping("/delete/{CommentId}")
    public Result deleteComments(@PathVariable int CommentId){
        int x = comContent.deleteComment(CommentId);
        if(x==1){
            return new Result(301,"删除成功");
        }
        return new Result(300,"删除失败");
    }
}
