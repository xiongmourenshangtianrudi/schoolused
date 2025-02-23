package com.schoolused.mapper;

import com.schoolused.entry.RegularUsers;
import com.schoolused.entry.commodity;
import com.schoolused.entry.orders;
import com.schoolused.entry.shoppingCar;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradingDao {
    @Select("select * from shopping_car where userid=#{userid}")//查找用户的购物车
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "com_id", property = "com_id"),
            @Result(column = "quantity", property = "quantity"),
            @Result(column = "com_id",property = "commodity",javaType= commodity.class,
                    one=@One(select ="com.schoolused.mapper.commodityDao.findCommodityid")
            )
    })
    public List<shoppingCar> findUsersShoppingCar(int userid);//查看用户所有的购物车
    @Insert("Insert into shopping_car(userid,com_id,quantity) values(#{userid},#{com_id},#{quantity})")
    public int addShoppingCar(shoppingCar shoppingCar);//新增商品到购物车

    //删除购物车
    @Delete("delete from shopping_car where id=#{id}")
    public int deleteShopping_car(int id);
    @Delete("delete from shopping_car where userid=#{userid}")
    public int cleanShopping_car(int userid);

    //管理员查找所有订单
    @Select("select Buyers_id,Seller_id,order_id,com_id,create_time,amount," +
            "Already_paid,quantity,state from orders limit #{page},#{size}")
    @Results({
            @Result(column = "buyers_id",property = "buyers_id"),
            @Result(column = "seller_id",property = "seller_id"),
            @Result(column ="order_id",property = "order_id"),
            @Result(column ="com_id",property = "com_id"),
            @Result(column ="create_time",property = "create_time"),
            @Result(column = "amount",property = "amount"),
            @Result(column = "already_paid",property = "already_paid"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "state",property = "state"),
            @Result(column = "com_id",property = "commodity",javaType= commodity.class,
                    one=@One(select ="com.schoolused.mapper.commodityDao.findCommodityid")
            )
    })
    public List<orders> getOrders(Map<String ,Object> map);


    //管理员通过商品id筛选对应的订单
    @Select("select Buyers_id,Seller_id,order_id,com_id,create_time,amount," +
            "Already_paid,quantity,state from orders where com_id = #{com_id} limit #{page},#{size}")
    @Results({
            @Result(column = "buyers_id",property = "buyers_id"),
            @Result(column = "seller_id",property = "seller_id"),
            @Result(column ="order_id",property = "order_id"),
            @Result(column ="create_time",property = "create_time"),
            @Result(column = "amount",property = "amount"),
            @Result(column = "already_paid",property = "already_paid"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "state",property = "state"),
            @Result(column = "com_id",property = "commodity",javaType= commodity.class,
                    one=@One(select ="com.schoolused.mapper.commodityDao.findCommodityid")
            ),
            @Result(column = "buyers_id",property = "buyers",javaType= RegularUsers.class,
                    one=@One(select = "com.schoolused.mapper.RegularUsersDao.findUsersById")
            )
    })
    public List<orders> getOrderbyid(Map<String ,Object> map);


    @Select("select count(*) from orders")
    public long allOrders();

    //查询购买的订单
    @Select("select Buyers_id,Seller_id,order_id,com_id,create_time,amount," +
            "Already_paid,quantity,state from orders where Buyers_id=${Buyers_id} and active=1")
    @Results({
            @Result(column = "buyers_id",property = "buyers_id"),
            @Result(column = "seller_id",property = "seller_id"),
            @Result(column ="order_id",property = "order_id"),
            @Result(column ="create_time",property = "create_time"),
            @Result(column = "amount",property = "amount"),
            @Result(column = "already_paid",property = "already_paid"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "state",property = "state"),
            @Result(column = "com_id",property = "commodity",javaType= commodity.class,
                    one=@One(select ="com.schoolused.mapper.commodityDao.findCommodityid")
            ),
            @Result(column = "buyers_id",property = "buyers",javaType= RegularUsers.class,
                    one=@One(select = "com.schoolused.mapper.RegularUsersDao.findUsersById")
            )
    })
    public List<orders> getOrdersByBuyers_id(int Buyers_id);


    //查询出售的订单
    @Select("select buyers_id,seller_id,order_id,com_id,create_time,amount," +
            "Already_paid,quantity,state from orders where Seller_id=${Seller_id}")
    @Results({
            @Result(column = "buyers_id",property = "buyers_id"),
            @Result(column = "seller_id",property = "seller_id"),
            @Result(column ="order_id",property = "order_id"),
            @Result(column ="create_time",property = "create_time"),
            @Result(column = "amount",property = "amount"),
            @Result(column = "already_paid",property = "already_paid"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "state",property = "state"),
            @Result(column = "com_id",property = "commodity",javaType= commodity.class,
                    one=@One(select ="com.schoolused.mapper.commodityDao.findCommodityid")
            ),
            @Result(column = "buyers_id",property = "buyers",javaType= RegularUsers.class,
                    one=@One(select = "com.schoolused.mapper.RegularUsersDao.findUsersById")
            )
    })
    public List<orders> getOrdersBySeller_id(int Seller_id);

    //创建订单
    @Insert("insert into orders(buyers_id,seller_id,order_id,create_time,com_id,amount,already_paid,quantity,state) values(${buyers_id},${seller_id},'${order_id}'," +
            "'${create_time}',${com_id},${amount},${already_paid},${quantity},'${state}')")
    public int CreateOrder(orders order);

    @Select("select buyers_id,seller_id,order_id,com_id,create_time,amount," +
            "Already_paid,quantity,state from orders where order_id='${order_id}'")
    @Results({
            @Result(column = "buyers_id",property = "buyers_id"),
            @Result(column = "seller_id",property = "seller_id"),
            @Result(column ="order_id",property = "order_id"),
            @Result(column ="create_time",property = "create_time"),
            @Result(column = "amount",property = "amount"),
            @Result(column = "already_paid",property = "already_paid"),
            @Result(column = "state",property = "state"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "com_id",property = "commodity",javaType= commodity.class,
                    one=@One(select ="com.schoolused.mapper.commodityDao.findCommodityid")
            )
    })
    public orders getOrderByid(String order_id);

    @Update("update orders set active = 0  where order_id = '${order_id}' ")
    public int cleanOrder(String order_id);

    @Delete("delete from orders where order_id = '${order_id}'")
    public int deleteOrder(String order_id);

    @Update("update orders set state='1',already_paid = 1 where order_id='${order_id}'")
    public int updateOrderState(String order_id);//将订单更新为已经支付状态

    @Update("update orders set state='2' where order_id='${order_id}'")
    public int updateOrderStatetoOver(String order_id);//将订单更新为完成状态
}
