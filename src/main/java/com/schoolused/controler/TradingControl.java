package com.schoolused.controler;

import com.schoolused.entry.orders;
import com.schoolused.entry.shoppingCar;
import com.schoolused.service.TradingDaoServe;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.List;

@RestController
//这是交易模块的api接口
@RequestMapping("/Trading")
public class TradingControl {
    @Autowired
    TradingDaoServe tradingDaoServe;
    @PostMapping
    public Result addShoppingCar(@RequestBody shoppingCar shoppingCar){//加入购物车
        int res = tradingDaoServe.addShoppingCar(shoppingCar);
        return new Result(301,res,"加入成功");
    }
    @GetMapping("/{userid}")
    public Result getShoppingCar(@PathVariable int userid){
        List<shoppingCar> rs = tradingDaoServe.getAllShoppingCar(userid);
        return new Result(301,rs,"获取成功");
    }
    @DeleteMapping("/{id}")
    public Result deleteShopping_Car(@PathVariable int id){//删除购物车里的物品
        int re = tradingDaoServe.deleteShopping_car(id);
        if(re >=0){
            return new Result(301,re,"删除成功");
        }
        return new Result(300,"删除失败");
    }
    @DeleteMapping("/clean/{userid}")
    public Result cleanShopping_Car(@PathVariable int userid){//清空购物车
        int re = tradingDaoServe.clearShopping_car(userid);
        if(re >=0){
            return new Result(301,re,"删除成功");
        }
        return new Result(300,"删除失败");
    }

    @PostMapping("/create_oerders")//产生多个订单
    public Result createOrders(@RequestBody List<orders> orders){
       int x =tradingDaoServe.createOrders(orders);
       if(x==1)
            return new Result(301,x,"创建成功");
       else
           return new Result(300,x,"创建失败");
    }

    @PostMapping("/create_order")
    public Result createOrder(@RequestBody orders orders){
        System.out.println(orders);
        //这里写业务层方法，产生订单
        String order_id = UUID.randomUUID().toString()+orders.getCom_id()+orders.getBuyers_id();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        Date createtime =new Date(System.currentTimeMillis());
        orders.setCreate_time(format.format(createtime));//将时间填入其中
        System.out.println(order_id);
        orders.setOrder_id(order_id);
        int x = tradingDaoServe.CreateOrder(orders);

        if(x!=0){
            return new Result(301,order_id,"订单创建成功");
        }else{
            return new Result(300,"订单创建失败");
        }

    }

    @GetMapping("/getOrder/{order_id}")
    public Result getOrder(@PathVariable String order_id){

        //生成订单信息后获取信息详细信息并进行支付操作
        return new Result(301,tradingDaoServe.getOrdersByid(order_id),"获取成功");
    }

    @GetMapping("/myOrders/{buyer_id}")//查看用户的购买订单
    public Result myOrders(@PathVariable int buyer_id){
        List<orders> myorders=tradingDaoServe.getOrdersBybuyers(buyer_id);
        if(myorders!=null)
            return new Result(301,myorders,"获取成功");
        return new Result(300,myorders,"获取失败");
    }

    @GetMapping("/mySeller/{seller_id}")//查看用户的出售订单
    public Result mySell(@PathVariable int seller_id){
        List<orders> myorders=tradingDaoServe.getOrdersBysellers(seller_id);
        if(myorders!=null)
            return new Result(301,myorders,"获取成功");
        return new Result(300,myorders,"获取失败");
    }

    @GetMapping("/recipt/{order_id}") //确认收货，订单完成
    public Result Receipt(@PathVariable String order_id){

        int x = tradingDaoServe.overOrders(order_id);

        if(x==1){

            return new Result(301,"订单已完成");
        }else{
            return new Result(300,"失败");
        }

    }

    @DeleteMapping("/cleanOrder/{order_id}")
    public Result cleanOrder(@PathVariable String order_id){
        int x =tradingDaoServe.cleanOrder(order_id);
        if(x ==301){
            return new Result(x,"删除成功");
        }else{
            return new Result(300,"删除失败");
        }

    }
    @PostMapping("/trade")
    public Result trade(String order_id){//交易接口
        //TODO 这里写交易方式
        orders orders =tradingDaoServe.getOrdersByid(order_id);//获取对象
       // long amount =orders.getAmount();/ /获取金额

        return new Result(301,orders,"获取成功");
    }
    @GetMapping("/current")
    public String currentString(){
        String order_id =  new Date().toString() +UUID.randomUUID().toString();
        return order_id;
    }

//    @GetMapping("/allOrders")
//    public Result getAllOrders(){
//
//
//        return null;
//    }

    @GetMapping("/refund/{order_id}")
    public Result suRefund(@PathVariable String order_id){


        return null;
    }
}
