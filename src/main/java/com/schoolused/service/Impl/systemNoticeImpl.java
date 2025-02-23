package com.schoolused.service.Impl;


import com.schoolused.entry.admin.systemNotice;
import com.schoolused.mapper.systemNoticeMapper;
import com.schoolused.service.systemNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class systemNoticeImpl implements systemNoticeService {

    @Autowired
    systemNoticeMapper systemNoticeMapper;

    @Override
    public List<systemNotice> getNotice(int id) {
        List<systemNotice> notices = systemNoticeMapper.getSystemNotices(id);

        return notices;
    }

    @Override
    public int insertNotice(systemNotice systemNotice) {
        int x = systemNoticeMapper.insertinfo(systemNotice);
        return x;
    }
}
