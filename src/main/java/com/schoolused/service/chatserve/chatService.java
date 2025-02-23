package com.schoolused.service.chatserve;

import com.schoolused.entry.chatContants;
import com.schoolused.entry.chatGroups;

import java.util.List;

public interface chatService {
    public List<chatGroups> getchatGroups(int userid);//通过用户id获取到用户的所有聊天记录
    public List<chatContants> getchatContants(int userid);//通过用户聊天组id获取聊天记录
    public int addChatcontants(chatContants chatContants);//加入聊天记录

    public int newChat(chatGroups chatGroups);
}
