package com.schoolused.service.Impl;

import com.schoolused.entry.userFund;
import com.schoolused.mapper.TradingDao;
import com.schoolused.mapper.fundDao;
import com.schoolused.service.fundServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class fundServeImpl implements fundServe {
    @Autowired
    fundDao fundDao;
    @Override
    public int createFund(userFund fund) {
        int x =0;
        if(fundDao.getUsrFund(fund.getUserid())!=null){
            return x;
        }else{
            x =fundDao.createFund(fund);
        }

        return x;
    }

    @Override
    public userFund getFund(int userid) {//获取个人账户信息
        userFund fund = null;
        if(userid>0){
            fund = fundDao.getUsrFund(userid);
        }
        return fund;
    }

    @Override
    public int rechargeSe(int userid, long money) {//充值接口

        int x = fundDao.addMoney(userid,money);

        return x;
    }

    @Override
    public int updateFund(int userid1, int userid2, float money) {//更新金额数据
        userFund userFund = getFund(userid1);
        if(userFund.getMoney()>=money){
            fundDao.loseMoney(userid1,money);
            fundDao.addMoney(userid2,money);
            return 301;
        }else{
            return 300;
        }
    }
}
