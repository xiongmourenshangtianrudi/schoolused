package com.schoolused.controler;

import com.schoolused.entry.RegularUsers;
import com.schoolused.mapper.RegularUsersDao;
import com.schoolused.service.Impl.RegularUserServeImpl;
import com.schoolused.service.RegularUserServe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/regularUser")
@CrossOrigin
public class RegularUsersControl {
    @Autowired
    RegularUserServeImpl regularUserServe;
    @Autowired
    RegularUsersDao regularUsersDao;

    @PostMapping("/upavator")
    public Result enrollus(int userid, MultipartFile uploadFiles )throws IOException {//更新用户头像
        System.out.print(uploadFiles.getOriginalFilename());
        String imagePath =  regularUserServe.saveAimage(uploadFiles);
        RegularUsers regularUsers = new RegularUsers();

//        regularUsers.setUserimage(imagePath);
//        System.out.println(regularUsers);
        regularUserServe.writeToDatabase(userid,imagePath);
        return new Result(301,imagePath,"更新用户头像");
    }
    @PostMapping
    public Result enrollus(@RequestBody RegularUsers regularUsers){
        int code = regularUserServe.enrrol(regularUsers);//接收业务层获取的返回码
        Result rs = null ;
       switch (code){
           case 301:
               rs = new Result(301,"注册成功");
               break;
           case 300:
              rs = new Result(code,"注册失败请输入账号");
               break;
           case 400:
               rs = new Result(code,"注册失败，用户名重复");

       }
        return rs;
    }


    @PostMapping("/login")
    public Result login(@RequestBody RegularUsers regularUsers){
        Result token = regularUserServe.login(regularUsers);
        if(token.getToken()!=null){
            return new Result(301, token.getData(),token.getToken(),"登录成功");
        }else if(token.getData()!=null){
            if(token.getData().toString().equals("0")) {
                return new Result(200, "登录失败,账号被封禁");//200账号封禁
            }else{
                return new Result(300,"登录失败，账号或密码错误");//300账号密码错误
            }
        }else{
            return new Result(300,"登录失败，账号或密码错误");//300账号密码错误
        }

    }
    @GetMapping("/{userid}")//这里获取用户信息
    public Result getUserInfo(@PathVariable int userid){
        RegularUsers res = regularUserServe.getUserInfo(userid);
        if(res !=null){
            return new Result(301,res,"获取成功");
        }else{
            return new Result(300,"获取失败");
        }
    }
    @PostMapping("/changeUserInfo")
    public Result changeUserInfo(@RequestBody RegularUsers regularUsers){//更新用户信息
        System.out.println(regularUsers);
        Result x= regularUserServe.UpdateUserinfo(regularUsers);
       return x;
    }

    @GetMapping("/getAlluserinfo")
    public Result allUserInfo(){

        List<RegularUsers> data = regularUserServe.getAllUserInfo();
        if (data.size()>0){
            return new Result(301,data,"获取成功");
        }else{
            return new Result(300,"获取失败");
        }

    }

    @GetMapping("/makeUserban/{id}")//对用户进行状态转换
    public Result makeUserban(@PathVariable int id){
        boolean statecode = regularUserServe.banUser(id);
        if(statecode){//返回值是一
            return new Result(301,"封禁成功");
        }else {
            return new Result(300,"封禁失败");
        }


    }
    @GetMapping("/makeUserfree/{id}")//对用户进行解除封禁止
    public Result makeUserfree(@PathVariable int id){
        boolean statecode = regularUserServe.freeUser(id);
        if(statecode){//返回值是一
            return new Result(301,"解禁禁成功");
        }else {
            return new Result(300,"解禁失败");
        }


    }

}
