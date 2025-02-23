package com.schoolused.controler;


import com.schoolused.entry.orders;
import com.schoolused.entry.userFund;
import com.schoolused.entry.recharge;
import com.schoolused.service.Impl.TradingServeImpl;
import com.schoolused.service.fundServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fund") //fund是对钱包交易的处理
public class fundControl {

    @Autowired
    fundServe fundServe;

    @Autowired
    TradingServeImpl tradingServe;

    @GetMapping("/fund/{userid}")
    public Result getMyFund(@PathVariable int userid){
        System.out.println(userid);
        userFund fund =fundServe.getFund(userid);
        if(fund ==null){
            userFund userFund = new userFund();
            userFund.setUserid(userid);
            userFund.setMoney(0);
            userFund.setState("正常");
            System.out.println(userFund);
            fundServe.createFund(userFund);
            fund = fundServe.getFund(userid);
        }

        return new Result(301,fund,"获取成功");
    }
    @PostMapping("/recharge")
    public Result recharge(@RequestBody recharge recharge){
        //TODO 这里接收充值数据并调用充值业务 业务过程 查询密码

        int x =fundServe.rechargeSe(recharge.getId(),recharge.getMoney());

        return new Result(301,"充值成功");
    }
    @PostMapping("/pay")//支付接口
    public Result payMent(@RequestBody orders orders){
        System.out.print(orders);
        int payid = orders.getBuyers_id();
        long amount = orders.getAmount();
        System.out.println(orders);
        //TODO 写支付方法

        int code =fundServe.updateFund(payid,1,amount);
        if(code==301){
            tradingServe.updateOrderState(orders.getOrder_id());
            return new Result(301,"支付成功");
        }else{
            return new Result(300,"支付失败");
        }

    }

}
