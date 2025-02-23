package com.schoolused.service.Impl;

import com.schoolused.entry.admin.talkToUserInfo;
import com.schoolused.entry.commodity;
import com.schoolused.mapper.systemtalk;
import com.schoolused.until.logInfo;
import com.schoolused.until.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class systemTalkServiceImpl {
    @Autowired
    systemtalk systemtalk;

    public talkToUserInfo gettalkmsg(int id){//获取公告详情
        talkToUserInfo t =systemtalk.getallTalkInfo(id);
        logInfo.log(1,t.toString());
        return t;
    }

    public page<talkToUserInfo> gettalks(int page, int size){//查询公告
        Map<String ,Object> params = new HashMap<>();
        params.put("page",(page-1)*size);
        params.put("size",size);

        List<talkToUserInfo> talks = systemtalk.getAllTalk(params);
        page<talkToUserInfo> pages = new page<>();
        pages.setPage(page);
        pages.setSize(size);
        pages.setTotal(systemtalk.getTotal());
        pages.setData(talks);
        return pages;
    }

    public talkToUserInfo getTalkInfo(int id){//获取id对应的公告信息
        talkToUserInfo talk = systemtalk.getallTalkInfo(id);
        return talk;
    }

    @Transactional
    public int newTalktoUser(talkToUserInfo talkToUserInfo){
        if(talkToUserInfo.getTitle().trim() !=null && talkToUserInfo.getMsg().trim() !=null) {
            int newid = 0;
            boolean tiaojian = true;
            long maxid = systemtalk.getMaxid();
            while (tiaojian) {//找出新的id
                if (newid > maxid) {
                    tiaojian = false;
                } else {
                    newid++;
                }
            }

            int x = systemtalk.newtalktitle(newid, talkToUserInfo.getTitle());//插入id

            int y = systemtalk.newtalkmsg(newid, talkToUserInfo.getMsg());
            if (x > 0 && y > 0) {
                return 1;

            } else {
                return 0;
            }
        }else{
            return 0;
        }
    }
}
