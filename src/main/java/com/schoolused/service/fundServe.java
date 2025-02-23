package com.schoolused.service;

import com.schoolused.entry.userFund;
import org.springframework.transaction.annotation.Transactional;

public interface fundServe {
    public int createFund(userFund fund);

    public userFund getFund(int userid);

    @Transactional
    public int updateFund(int usrid1,int userid2,float money);

    public int rechargeSe(int userid,long money);
}
