package com.schoolused.service;

import com.schoolused.entry.admin.adminControlinfo;

import java.util.List;

public interface admincontrol {//用户管理器

    public int insertControl(adminControlinfo adminControlinfo);//插入操作记录
    public List<adminControlinfo> getAllContolInfo();//查询所有操作信息
    public List<adminControlinfo> getContrlInfo(int id);//查询操作记录




}
