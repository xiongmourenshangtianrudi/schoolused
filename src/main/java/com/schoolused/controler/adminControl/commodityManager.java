package com.schoolused.controler.adminControl;

import com.schoolused.controler.Result;
import com.schoolused.entry.admin.actionInfo;
import com.schoolused.entry.admin.adminControlinfo;
import com.schoolused.entry.commodity;
import com.schoolused.service.Impl.admincontrolImpl;
import com.schoolused.service.Impl.commodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admControl")
public class commodityManager {
    @Autowired
    admincontrolImpl admincontrol;

    @Autowired
    commodityServiceImpl commodityService;
    @GetMapping("/getinfo")//获取所有的操作记录获取商品的处理记录
    public Result getControlInfo(){
        List<adminControlinfo> adminControlinfo = admincontrol.getAllContolInfo();
        return new Result(301,adminControlinfo,"获取成功");
    }
    @GetMapping("/getinfo/{id}")//获取相关的管理员操作记录的id
    public Result getControlInfo(@PathVariable int id){
        List<adminControlinfo> adminControlinfo = admincontrol.getContrlInfo(id);
        return new Result(301,adminControlinfo,"获取成功");
    }

    @GetMapping("/getcommomdity/{id}")//注解的值必须用双引号
    public Result getCommodityByid(@PathVariable int id){
       commodity com= commodityService.findcomByid(id);
        Result re =null;
       if(com!=null){
           re = new Result(301,com,"获取成功");
       }else {
           re = new Result(300, "未找到");
       }
        return re;
    }



    @PostMapping("/takeoffCom")//管理员下架商品
    public Result takeOff(@RequestBody actionInfo actionInfo){
        int x = commodityService.takeOff(actionInfo.getTargetId());//下架商品
        if(x>0){

            adminControlinfo adminControlinfo = new adminControlinfo();
            adminControlinfo.setUseid(actionInfo.getAdUserInfo());
            adminControlinfo.setControlid(actionInfo.getTargetId());
            String msg = "管理员"+actionInfo.getAdUserInfo()+"将商品id为"+actionInfo.getTargetId()+"的商品进行了强制下架";
            adminControlinfo.setInfo(msg);
            int num = admincontrol.insertControl(adminControlinfo);
            return new Result(301,num,"操作成功");
        }else{
            return new Result(300,"操作失败");
        }

    }

}
