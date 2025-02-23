package com.schoolused.mapper;

import com.schoolused.entry.userFund;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface fundDao {
    @Insert("insert into userfund(userid,money,state) values(${userid},${money},'${state}')")//产生余额数据
    public int createFund(userFund fund);

    @Select("select userid,money,state from userfund where userid=#{userid} ")
    public userFund getUsrFund(int userid);

    @Update("update userfund set money=money+${money} where userid=${userid}")
    public int addMoney(int userid,float money);//收入钱

    @Update("update userfund set money=money-${money} where userid=${userid}")
    public int loseMoney(int userid,float money);//扣除钱



}
