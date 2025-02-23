package com.schoolused.service.Impl;

import com.schoolused.Component.WebSocketServe;
import com.schoolused.entry.commodity;
import com.schoolused.entry.orders;
import com.schoolused.entry.shoppingCar;
import com.schoolused.mapper.TradingDao;
import com.schoolused.mapper.userSub;
import com.schoolused.service.TradingDaoServe;
import com.schoolused.until.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schoolused.entry.userSubEntry;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TradingServeImpl implements TradingDaoServe {
    @Autowired
    TradingDao tradingDao;

    @Autowired
    fundServeImpl fundServe;

    @Autowired
    userSub userSub;

    @Autowired
    WebSocketServe webSocketServe;


    @Override
    public List<shoppingCar> getAllShoppingCar(int userid) {
        List<shoppingCar> res = tradingDao.findUsersShoppingCar(userid);
        if(res.size()>0){
            return res;
        }else {
            return null;
        }
    }

    @Override
    public page<orders> getAllOrders(int size, int page) {//分页查询订单
        Map<String ,Object> param = new HashMap<>();
        param.put("page",(page-1)*size);
        param.put("size",size);
        List<orders> ordersList = tradingDao.getOrders(param);
        com.schoolused.until.page<orders> pager = new page<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setData(ordersList);
        pager.setTotal(tradingDao.allOrders());
        return pager;
    }

    public page<orders> getOrderByComId(int size,int page , int com_id)//通过商品查询对于的订单信息
    {
        Map<String ,Object> param = new HashMap<>();
        param.put("page",(page-1)*size);
        param.put("size",size);
        param.put("com_id",com_id);
        List<orders> ordersList = tradingDao.getOrderbyid(param);
        page<orders> pager = new page<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setData(ordersList);
        pager.setTotal(tradingDao.allOrders());
        return pager;
    }

    @Override
    public int addShoppingCar(shoppingCar shoppingCar) {//新增收藏
        int num =tradingDao.addShoppingCar(shoppingCar);
        return num;
    }

    @Override
    public int deleteShopping_car(int id) {//删除收藏中的某个商品
        int num =tradingDao.deleteShopping_car(id);
        if(num>0){
            return num;
        }
        return 0;
    }

    @Override
    public int clearShopping_car(int userid) {//清空收藏
        int num =tradingDao.cleanShopping_car(userid);
        if(num>0){
            return num;
        }
        return 0;
    }

    @Override
    public int CreateOrder(orders order) {//创建订单
        System.out.println(order.getBuyers_id());
        order.setState("0");
        if(order.getBuyers_id() !=0){
            int num = tradingDao.CreateOrder(order);
            return num;
        }else{
            return 0;
        }

    }

    @Override
    public List<orders> getOrders(int Seller_id) {
        List<orders> orders=null;
        if(Seller_id>0) {
            orders = tradingDao.getOrdersBySeller_id(Seller_id);
        }
        return orders;
    }

    @Override
    public List<orders> getOrdersBybuyers(int buyer_id) {
        List<orders> orders=null;
        if(buyer_id>0){
            orders = tradingDao.getOrdersByBuyers_id(buyer_id);
        }
        return orders;
    }

    @Override
    public List<orders> getOrdersBysellers(int seller_id) {
        List<orders> orders=null;
        if(seller_id>0){
            orders = tradingDao.getOrdersBySeller_id(seller_id);
        }
        return orders;
    }

    public orders getOrdersByid(String order_id){//获取立即支付时产生的订单
        orders orders = null;
        if(order_id!=null){
            orders = tradingDao.getOrderByid(order_id);
        }

        return orders;
    }

    @Override
    public int cleanOrder(String order_id) {
       String id=order_id.trim();
       int x = tradingDao.cleanOrder(id);
       if(x>0){
           return 301;
       }
        return 0;
    }


    @Override
    public int updateOrderState(String order_id) {//当支付成功时，回调该处函数更新订单信息
        int x = tradingDao.updateOrderState(order_id);//更新订单信息

        return x;
    }

    @Override
    public int overOrders(String order_id) {
        String state ="1";
        orders orders = tradingDao.getOrderByid(order_id);
        //System.out.println(orders);
        boolean b = orders.getState().equals(state);
        //System.out.println(b);
        if(b){
            int y =fundServe.updateFund(1,orders.getSeller_id(),orders.getAmount());//这里是金额转移
            //System.out.println(y);
            if(y==301){
                int x = tradingDao.updateOrderStatetoOver(order_id);//更新订单信息
                return x;
            }
            return 0;

        }else{
            return 0;
        }

    }

    public int makeOrderDelete(String order_id){

        return tradingDao.deleteOrder(order_id);
    }

    @Override
    public int createOrders(List<orders> orders){
        int num =0;
        for(int i = 0;i<orders.size();i++){
            String order_id = UUID.randomUUID().toString()+orders.get(i).getCom_id()+orders.get(i).getBuyers_id();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            Date createtime =new Date(System.currentTimeMillis());
            orders.get(i).setCreate_time(format.format(createtime));//将时间填入其中
            System.out.println(order_id);
            orders.get(i).setOrder_id(order_id);
            int x = this.CreateOrder(orders.get(i));
            if(x>0)
                num+=1;
        }
        if(orders.size()==num)
            return 1;
        else
            return 0;
    }

    @Override
    public int reFund(int userid, String order_id) {
        userSubEntry userSubEntry = new userSubEntry();
        userSubEntry.setUserid(userid);
        userSubEntry.setMessage("用户发起了对订单id"+order_id+"的退款申请");
        userSubEntry.setState(0);
        userSub.newMessage(userSubEntry);


        return 0;
    }
}
