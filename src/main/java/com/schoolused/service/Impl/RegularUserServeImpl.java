package com.schoolused.service.Impl;

import com.schoolused.controler.Result;
import com.schoolused.controler.token.token;
import com.schoolused.entry.RegularUsers;
import com.schoolused.mapper.RegularUsersDao;
import com.schoolused.service.RegularUserServe;
import com.schoolused.until.jwtuntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RegularUserServeImpl implements RegularUserServe {

    @Autowired
    RegularUsersDao regularUsersDao;
    @Autowired
    jwtuntil jwtuntil;
    @Autowired
    token token;
    @Override
    public String saveAimage(MultipartFile file)throws IOException {//保存图片并产生图片url
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
        String path =   ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/avatar/";
        String filePath = saveFile(file,path);
        return filePath;
    }

    private String saveFile(MultipartFile file, String path)throws IOException {//将文件保存到服务器的地址
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString()+file.getOriginalFilename();
        File file1 = new File(path+uuid );
        file.transferTo(file1);
        return "http://localhost:8083"+"/avatar/"+uuid;
    }

    public int writeToDatabase(int id,String imgurl){//注册用户,向数据库中更行头像信息
        return regularUsersDao.changeavator(id,imgurl);
    }
    @Deprecated
    public int enrrol(RegularUsers regularUsers){//注册用户
        int res =0;

        if(regularUsers.getUserName()!=""&&regularUsers.getUserPwd()!="") {

            if (regularUsersDao.checkUserRepeat(regularUsers) == 0)//验证用户名是否重复
                 {
                     res=regularUsersDao.enroll(regularUsers);
                     if(res>0){
                         return 301;
                     }else {
                         return 300;
                     }
                }else{
                return 400;
                //400表示用户名字重复
            }
       }else{
            return 300;
        }
    }

    @Override
    public boolean banUser(int id) {//通过id封禁用户
        int x = regularUsersDao.makeuserban(id);//将用户状态修改为封禁并做出提示
        RegularUsers user = regularUsersDao.findUsersById(id);
        if(x>0){
            String banUserToken =token.getToken(user.getUserName());
            if(banUserToken!=null){
                token.cleanToken(user.getUserName());
            }
            return true;//封禁成功的
            //TODO 这里需要将登录状态的token清楚
        }else{
            return false;
            //封禁失败 系统错误
        }

    }
    @Override
    public boolean freeUser(int id) {//通过id封禁用户
        int x = regularUsersDao.makeuserfree(id);//将用户状态修改为封禁并做出提示
        if(x>0){
            return true;//封禁成功的
            //TODO 这里需要将登录状态的token清楚
        }else{
            return false;
            //封禁失败 系统错误
        }

    }

    public Result login(RegularUsers regularUsers){//登录逻辑

        RegularUsers user = regularUsersDao.findUsersByName(regularUsers);
        System.out.println(user);
        if(user!=null){
            if(user.getState()!=0){
                if(regularUsers.getUserPwd().equals(user.getUserPwd())){
                    String Token = jwtuntil.jwt(user.getUserName(), user.getUserPwd(),"person");//生成token
                    token.saveToken(user.getUserName(),Token);
                    //System.out.println(token.getToken(user.getUserName()));
                    return new Result(301,user,Token,"登录成功");
                }else{
                    return new Result(300,null,null,"登录失败，账号或密码错误");
                }

            }else{
                return new Result(200,user.getState(),null,"登录失败，账号被封禁");
            }
        }else{
            return  new Result(300,null,null,"登录失败，账号或密码错误");
        }



    }


    @Override
    public RegularUsers getUserInfo(int userid) {
        if(userid>=0){
            return regularUsersDao.findUsersById(userid);
        }else{
            return null;
        }

    }

    @Override
    public Result UpdateUserinfo(RegularUsers regularUsers) {
        int resnum = regularUsersDao.changeUserInfo(regularUsers);//更新用户信息并将更新的条数返回
        if(resnum>=1){
            return new Result(301,"更新成功");
        }else{
            return new Result(300,"更新失败");
        }

    }

    @Override
    public List<RegularUsers> getAllUserInfo() {//查询所有的用户信息
        List<RegularUsers> regularUsers=null;

        regularUsers = regularUsersDao.findAlluserInfo();//查询所有的用户信息


        return regularUsers;
    }
}
