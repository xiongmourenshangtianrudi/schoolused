package com.schoolused.service.Impl;

import com.schoolused.Component.WebSocketServe;
import com.schoolused.entry.admin.adminControlinfo;
import com.schoolused.entry.admin.systemNotice;
import com.schoolused.mapper.adminContrl;
import com.schoolused.service.admincontrol;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class admincontrolImpl implements admincontrol {//管理员操作控制
    @Autowired
    adminContrl adminContrl;

    @Autowired
    systemNoticeImpl systemNotice;

    @Autowired
    commodityServiceImpl commodityService;
    @Autowired
    WebSocketServe webSocketServe;
    @Override
    public int insertControl(adminControlinfo adminControlinfo) {
        System.out.println(adminControlinfo.toString());
        int x = adminContrl.insertControlInfo(adminControlinfo);//往数据库中插入数据
        int userid = commodityService.getCom_userid(adminControlinfo.getControlid());//在这里获取到用户id
        //封装数据
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String datetime = formatter.format(date);
        systemNotice notice = new systemNotice();
        notice.setAdminid(adminControlinfo.getUseid());
        notice.setUserid(userid);
        notice.setInfo("由于你的商品违规，因此:"+adminControlinfo.getInfo());
        notice.setTitle("下架通知");
        notice.setCreatetime(datetime);
        if(userid !=0){
            systemNotice.insertNotice(notice);//将记录放入数据库
            webSocketServe.sendWarnInfo(notice,userid);//发送到指定的用户

        }


        return x>0?301:300;
    }

    @Override
    public List<adminControlinfo> getAllContolInfo() {//查询所有的处理记录
        List<adminControlinfo> adminControlinfo = adminContrl.getAllControlInfo();
        return adminControlinfo;
    }

    @Override
    public List<adminControlinfo> getContrlInfo(int id) {//查询某管理员的控制信息
        List<adminControlinfo> adminControlinfo = adminContrl.getControlInfobyUsedId(id);
        return adminControlinfo;
    }
}
