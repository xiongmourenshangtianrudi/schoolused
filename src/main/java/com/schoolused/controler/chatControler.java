package com.schoolused.controler;

import com.schoolused.entry.chatGroups;
import com.schoolused.service.chatserve.impl.chatServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat")
public class chatControler {
    @Autowired
    chatServiceImp chatServiceImp;
    @GetMapping("/{userid}")
    public Result getChatgroups(@PathVariable int userid){
        System.out.println("fangwen");
        return new Result(301,chatServiceImp.getchatGroups(userid),"成功") ;
    }
    @GetMapping("/chatContant/{cid}")
    public Result getChatContants(@PathVariable int cid){
        return new Result(301,chatServiceImp.getchatContants(cid),"成功") ;
    }
    @PostMapping
    public Result addNewChat(@RequestBody chatGroups chatGroups){
        System.out.println(chatGroups);
        int data = chatServiceImp.newChat(chatGroups);
        return new Result(data,"加入聊天组");
    }
}
