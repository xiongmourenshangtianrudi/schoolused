package com.schoolused.until;

import com.schoolused.entry.chatGroups;
import com.schoolused.service.chatserve.impl.chatServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Theads extends Thread {
    private int userid;
    private chatServiceImp chatServiceImp;
    public Theads (int id ,chatServiceImp chatServiceImp){
        this.userid =id;
        this.chatServiceImp =chatServiceImp;
    }


    @Override
    public void run() {
        System.out.println(chatServiceImp.getchatGroups(userid));
    }
}
