package com.schoolused.service.chatserve.impl;

import com.schoolused.entry.chatContants;
import com.schoolused.entry.chatGroups;
import com.schoolused.mapper.chatMapper;
import com.schoolused.service.chatserve.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class chatServiceImp implements chatService {

    @Autowired
    chatMapper chatMapper;
    @Override
    public List<chatGroups> getchatGroups(int userid) {//获取用户的聊天记录
        List<chatGroups> chatGroups = chatMapper.findAllChatGroups(userid);

      return chatGroups;
    }

    @Override
    public List<chatContants> getchatContants(int cid) {
        List<chatContants> chatContants = chatMapper.findAllChatcontant(cid);
        return chatContants;
    }

    @Override
    public int addChatcontants(chatContants chatContants) {
      int num= chatMapper.addChatInfo(chatContants);
        return num;//后面在加
    }

    @Override
    public int newChat(chatGroups chatGroups) {
        int x =chatMapper.isFirstChat(chatGroups);
        if(x==1){
            System.out.print(x);
            return 0;
        }else{
            if(chatGroups.getUid1()==0 || chatGroups.getUid2()==0){//避免出现未登录就发起聊天的情况
                return 0;
            }else{
                chatMapper.addNewChat(chatGroups);//如果是第一次聊天则发起聊天
                return 1;
            }
        }

    }

    public chatContants getchatcontant(int cid){
        chatContants chatContants = chatMapper.findAllChatcontant1(cid);
        return chatContants;
    }

}
