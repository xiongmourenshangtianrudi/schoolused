package com.schoolused.until;

import com.schoolused.entry.admin.systemNotice;
import com.schoolused.entry.commodity;
import com.schoolused.mapper.adminContrl;
import com.schoolused.mapper.commodityDao;
import com.schoolused.service.Impl.systemNoticeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.schoolused.Component.WebSocketServe;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@EnableScheduling
@Component
public class shenHeList {



    private List<commodity> shenhelist = new ArrayList<commodity>();
//    private List<Map<Integer,commodity>> shenhezhonging = new ArrayList<Map<Integer,commodity>>();
    private List<commodity> shenheing = new ArrayList<commodity>();
    @Autowired
    commodityDao commodityDao;

    @Autowired
    WebSocketServe webSocketServe;

    @Autowired
    systemNoticeImpl systemNoticeControl;

    public List<commodity> getShenhelist(){
        return shenhelist;
    }

    public List<commodity> getShenheing(){
        return shenheing;
    }

    @Scheduled(fixedRate = 300000)
    public void tasks(){
        this.shenhelist = commodityDao.findCommodityallShenhe();

        if(!this.shenhelist.isEmpty()){
            System.out.println(shenhelist.get(0));
        }
        logInfo.log(10,"已经刷新");
    }



    public void setShenhelist(commodity commodity){
        shenhelist.add(commodity);
    }
    public void setShenheing(commodity commodity){
        shenheing.add(commodity);
    }


    public commodity getlist(int id){
        commodity shenhezhonging = null;
        for(int i=0;i<shenhelist.size();i++){
            if(id == shenhelist.get(i).getCom_id()){
             shenhezhonging = shenhelist.get(i);
             shenhelist.remove(i);
             break;
            }
        }
        return shenhezhonging;
    }


    public int updateList(int id){
        commodity currentcommodity = this.getlist(id);
        if(currentcommodity !=null){
            this.setShenheing(currentcommodity);
            logInfo.log(11,"已加入审核队列");
            return 1;
        }
        return 0;
    }



    public int shenHeControl(int id ,int state) {
            int x=0;
        systemNotice shenheinfo = new systemNotice();
        String info ;
        if(state ==1){  //state==1是审核通过  state == 0 审核是失败的
             x =commodityDao.shenhepass(id);
             info = "审核通过,已经再平台发布";
        }else{
            x= commodityDao.shenhefall(id);
            info = "审核未通过，已经被下架，请修改后重试 ";
        }

        if(x==1){
            for (int i = 0; i < shenheing.size(); i++) {
                if (id == shenheing.get(i).getCom_id()) {
                    shenheinfo.setAdminid(1);
                    shenheinfo.setCreatetime(createCurrenttime());
                    shenheinfo.setTitle("审核信息");
                    shenheinfo.setInfo("你的商品"+shenheing.get(i).getCom_name()+info);
                    shenheinfo.setUserid(shenheing.get(i).getCom_userid());
                    sendShenheInfoToUser(shenheinfo);
                    shenheing.remove(i);
                    break;
                }
            }
            return 1;
        }else{
            return 0;
        }


    }


    private String createCurrenttime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String datetime = formatter.format(date);
        return datetime;
    }

    private void sendShenheInfoToUser(systemNotice systemNotice){//发送给用户

        systemNoticeControl.insertNotice(systemNotice);
        webSocketServe.sendWarnInfo(systemNotice,systemNotice.getUserid());
    }
}
