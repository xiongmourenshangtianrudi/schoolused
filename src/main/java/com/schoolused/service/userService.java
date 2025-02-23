package com.schoolused.service;

import com.schoolused.controler.code;
import com.schoolused.entry.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface userService {
    public List<User> findAllUser();//查询所有用户
    public String loginService(User userInfo);//登录业务

    public User getUser(User userInfo);//获取用户信息

    public int loginOut(String key);//登出

    public int insertUserServe(User user);//新增用户

    public int updateUserServe(User user);//更新用户信息
    public int deleteServe(List<Integer> id);//删除多个user
    public int deleteServe(int userid);//删除一个user
}
