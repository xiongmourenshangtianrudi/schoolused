package com.schoolused.service;

import com.schoolused.entry.commentContent;

import java.util.List;

public interface commentContentServce {
    public List<commentContent> getCommentByCom_id(int com_id);
    public int newComent(commentContent comment);
    public int deleteComment(int CommentID);

}
