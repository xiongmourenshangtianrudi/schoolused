package com.schoolused.service;

import com.schoolused.entry.orders;
import com.schoolused.entry.shoppingCar;
import com.schoolused.until.page;

import java.util.List;
import java.util.Map;

public interface TradingDaoServe {
    public int addShoppingCar(shoppingCar shoppingCar);
    public List<shoppingCar> getAllShoppingCar(int userid);
    public int deleteShopping_car(int id);
    public int clearShopping_car(int userid);
    public int CreateOrder(orders order);
    public page<orders> getAllOrders(int size,int page);//管理员用于获取所有的订单信息
    public List<orders> getOrders(int Seller_id);
    public List<orders> getOrdersBybuyers(int buyer_id);
    public List<orders> getOrdersBysellers(int seller_id);

    public orders getOrdersByid(String order_id);
    public int cleanOrder(String order_id);
    public int updateOrderState(String order_id);
    public int overOrders(String order_id);
    public int createOrders(List<orders> orders);

    public int reFund(int userid,String order_id);
}
