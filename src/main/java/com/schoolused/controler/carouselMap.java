package com.schoolused.controler;

import com.schoolused.entry.admin.talkToUserInfo;
import com.schoolused.entry.indexMapUrl;
import com.schoolused.service.Impl.RegularUserServeImpl;
import com.schoolused.service.Impl.indexMapserviceImpl;
import com.schoolused.service.Impl.systemTalkServiceImpl;
import com.schoolused.until.logInfo;
import com.schoolused.until.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/carouserlmap")
public class carouselMap                      {//存放关于主页投放的有关消息
    @Autowired
    indexMapserviceImpl indexMapservice;
    @Autowired
    RegularUserServeImpl regularUserServe;

    @Autowired
    systemTalkServiceImpl systemTalkService;//公告业务层

    @GetMapping
    public Result indexMap(){//获取所有的轮播图
        List res =  indexMapservice.getIndexMap();

        return new Result(301,res,"获取成功");
    }


    @PostMapping("/updateCarouserlmap")
    public Result updateCarouserMap() {//更新一条轮播图
        //TODO 这里更新一条轮播图
        return null;
    }
    @PostMapping("/updateCarouserlmaps")
    public Result updateCarouserMaps() {//更新多条轮播图
        //TODO 这里更新多条轮播图

        return null;
    }

    @PostMapping("/newMap")
    public Result newMap(int rating,MultipartFile uploadFiles) throws IOException{//在这里进行上传图片并进行处理

        String imgeurl = regularUserServe.saveAimage(uploadFiles);//这里上传图片并生成图片url
        indexMapUrl indexMapUrl =  new indexMapUrl(rating,imgeurl);//这里生成图品上传后的放入数据库的对象
        int charuNum = indexMapservice.submitNewMap(indexMapUrl);//将插入成功的数量返回

        return charuNum>0?new Result(301,charuNum,"新曾成功"):new Result(300,"新增失败");//返回更新结果
    }
    @GetMapping("/deleteall")
    public Result DeleteAllMaps(){//删除所有轮播图
        int x =indexMapservice.deleteAllMap();
        return  x >0 ?new Result(301, x, "删除成功") : new Result(300, "删除失败");
    }


    @PostMapping("/newtalktouser")

    public Result newTalkToUser(@RequestBody talkToUserInfo talkToUserInfo){
        logInfo.log(1,talkToUserInfo.toString());
        return null;
    }

    @GetMapping("/getAllTalktoUser/{page}/{size}")
    public Result getAllTalkToUser(@PathVariable int page,@PathVariable int size){
        page<talkToUserInfo> talkToUserInfopage = systemTalkService.gettalks(page,size);
        if(talkToUserInfopage !=null){
            return new Result(301,talkToUserInfopage,"获取成功");
        }else{
            return new Result(300,"获取失败");
        }

    }

    @GetMapping("/getAllTalktoUserinfo/{id}")
    public Result getTalkToUser(@PathVariable int id){

        talkToUserInfo talk = systemTalkService.getTalkInfo(id);//获取公告对象

        return new Result(301,talk,"获取成功");
    }

    @PostMapping("/newTalktoUser")
    public Result newTalkToUserInfo(@RequestBody talkToUserInfo talkToUserInfo){
        logInfo.log(5,talkToUserInfo.toString());
        int x = systemTalkService.newTalktoUser(talkToUserInfo);
        if(x==1){
            return new Result(301,"发布成功");

        }else {
            return new Result(300,"发布失败");
        }

    }
}
