package com.schoolused.service;


import com.schoolused.entry.admin.systemNotice;

import java.util.List;

public interface systemNoticeService {


    public List<systemNotice> getNotice(int id);//获取系统通知

    public int insertNotice(systemNotice systemNotice);//插入系统通知
}
