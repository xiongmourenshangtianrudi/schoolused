package com.schoolused.service;

import com.schoolused.controler.Result;
import com.schoolused.entry.RegularUsers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface RegularUserServe {
    public String saveAimage(MultipartFile file) throws IOException;

    public Result login(RegularUsers regularUsers);//登录接口返回token

    public RegularUsers getUserInfo(int userid);//获取用户信息

    public Result UpdateUserinfo(RegularUsers regularUsers);//更新用户信息

    public List<RegularUsers> getAllUserInfo();//获取所有的用户信息

    public boolean banUser(int id);//封禁用户

    public boolean freeUser(int id);//解禁用户
}
