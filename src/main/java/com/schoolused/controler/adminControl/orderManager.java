package com.schoolused.controler.adminControl;

import com.schoolused.controler.Result;
import com.schoolused.entry.orders;
import com.schoolused.service.Impl.TradingServeImpl;
import com.schoolused.until.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/adminOrder")
public class orderManager {

    @Autowired
    TradingServeImpl tradingServe;

    @GetMapping("/getallOrders/{page}/{size}")//
    public Result getAllOrders(@PathVariable("size") int size, @PathVariable("page") int page){//查询所有的订单信息
        Result rs =null;
        page<orders> pages =tradingServe.getAllOrders(size,page);

        if(pages.getData().size() !=0){
            rs = new Result(301,pages,"获取成功");
        }else{
            rs = new Result(300,"获取失败");
        }
       return rs;
    }

    @GetMapping("/takeoffOrder/{order_id}")//删除失效订单
    public Result delOrders(@PathVariable("order_id") String order_id){
        //System.out.println(order_id);
        int x = tradingServe.makeOrderDelete(order_id);

        return new Result(301,x,"删除成功");
    }

    @GetMapping("/getOrdersByComid/{page}/{size}/{comid}")//获取对应商品id
    public Result getBYcomid(@PathVariable int comid,@PathVariable("size") int size, @PathVariable("page") int page){

        Result rs =null;
        page<orders> pages =tradingServe.getOrderByComId(size,page,comid);

        if(pages.getData().size() !=0){
            rs = new Result(301,pages,"获取成功");
        }else{
            rs = new Result(300,"获取失败");
        }
        return rs;
    }


}
