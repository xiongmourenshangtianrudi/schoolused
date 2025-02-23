package com.schoolused.mapper;

import com.schoolused.entry.RegularUsers;
import com.schoolused.entry.commodity;
import com.schoolused.entry.image;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface commodityDao {

    @Select("select * from commodity")
    public List<commodity> findAllcom();

    @Select("select * from commodity where com_id = ${id}")
    @Results({
            @Result(column="com_userid", property="Regularuser", javaType=RegularUsers.class,
                    one=@One(select = "com.schoolused.mapper.RegularUsersDao.findUsersById")),
            @Result(column = "com_id",property = "com_id"),
            @Result(column = "com_name",property = "com_name"),
            @Result(column = "com_stock",property = "com_stock"),
            @Result(column = "com_type",property = "com_type"),
            @Result(column = "com_userid",property = "com_userid"),
            @Result(column = " util",property = "util"),
            @Result(column = "com_description",property = "com_description"),
            @Result(column = "time",property = "time"),
            @Result(column = "img",property = "img"),
//            @Result(column = "com_id",property = "images",javaType = List.class,
//            many = @Many(select = "com.schoolused.mapper.commodityDao.findAllImageByid"))
            @Result(column="com_id", property="images", javaType=List.class,
                    many=@Many(select="findAllImageByid"))
    })
        public commodity findcommodityByid(int id);//多表查询，T商品详情页请求

    @Select("select * from commodity where com_id = ${id} ")
    @Results({
            @Result(column="com_userid", property="Regularuser", javaType=RegularUsers.class,
                    one=@One(select = "com.schoolused.mapper.RegularUsersDao.findUsersById")),
            @Result(column = "com_id",property = "com_id"),
            @Result(column = "com_name",property = "com_name"),
            @Result(column = "com_stock",property = "com_stock"),
            @Result(column = "com_type",property = "com_type"),
            @Result(column = "com_userid",property = "com_userid"),
            @Result(column = " util",property = "util"),
            @Result(column = "com_description",property = "com_description"),
            @Result(column = "time",property = "time"),
            @Result(column = "img",property = "img"),
//            @Result(column = "com_id",property = "images",javaType = List.class,
//            many = @Many(select = "com.schoolused.mapper.commodityDao.findAllImageByid"))
            @Result(column="com_id", property="images", javaType=List.class,
                    many=@Many(select="findAllImageByid"))
    })
    public commodity findCommodityid(int id);//通过商品id去查询商品，不查询详细信息

    @Select("select * from com_imageurl where commodity_id = #{id}")
        public  List<image> findAllImageByid(int id);


    @Select("select * from commodity where com_userid = #{userid} and active = 1")
    public List<commodity> findCommodityByUserId(int userid);//通过用户id查询用户发布的商品


    @Select("select * from commodity where com_userid = #{userid} and active = 2")
    public List<commodity> findCommodityByUserIdShenhe(int userid);//通过用户id查询用户发布的审核中的商品

    @Select("select * from commodity where active = 2")
    public List<commodity> findCommodityallShenhe();//通过用户id查询用户发布的审核中的商品

    @Update("update commodity set active =1 where com_id = #{com_id}")
    public int shenhepass(int com_id);
    @Update("update commodity set active =0 where com_id = #{com_id}")
    public int shenhefall(int com_id);

    @Select("select * from commodity limit #{page},#{size}")
    @Results({
            @Result(column="com_userid", property="Regularuser", javaType=RegularUsers.class,
                    one=@One(select = "com.schoolused.mapper.RegularUsersDao.findUsersById")),
            @Result(column = "com_id",property = "com_id"),
            @Result(column = "com_name",property = "com_name"),
            @Result(column = "com_stock",property = "com_stock"),
            @Result(column = "util",property = "util"),
            @Result(column = "com_type",property = "com_type"),
            @Result(column = "com_userid",property = "com_userid"),
            @Result(column = " util",property = "util"),
            @Result(column = "com_description",property = "com_description"),
            @Result(column = "time",property = "time"),
            @Result(column = "img",property = "img")})
    public List<commodity> findAllcombypageAll(Map<String ,Object> map);//查找所有商品

    @Select("select * from commodity where active = 1 ORDER BY RAND() limit #{page},#{size}")
    List<commodity> findAllcombypage(Map<String ,Object> map);//查找活动的商品，在售商品
    @Select("select count(*) from commodity")
    long findAllcomCount();//总共的页数
    @Select("select * from commodity where com_type=#{type} and active = 1 limit #{page},#{size} ")
    List<commodity> findAllcombyId(Map<String ,Object> map);//通过类型查询商品
    @Select("select count(*) from commodity where com_type='${type}'")
    long findAllcomByIdCount(String type);//总共的页数
    //or com_description like '%#{keyword}%'
    @Select("select * from commodity where com_description like CONCAT('%', #{keyword}, '%') or com_name like CONCAT('%', #{keyword}, '%') limit #{page},#{size}")
    List<commodity> searchCombypage(Map<String ,Object> map);// TODO 关键词检索商品

    @Select("select com_userid from commodity where com_id = #{com_id}")
    public int getCom_userid(int com_id);

    @Insert("insert into commodity(com_name,com_price,com_stock,util,com_type,img,com_userid,com_description,time)" +
            "values('${com_name}',${com_price},${com_stock},'${util}','${com_type}','${img}',${com_userid},'${com_description}',NOW())")
    public int newItem(commodity commodity);

    @Insert("insert into com_imageurl(commodity_id,com_imge_url) values(${commodity_id},'${com_imge_url}')")
    public int addCommodityimge(int commodity_id,String com_imge_url);//为商品添加细节图片

    @Update("update commodity set com_name='${com_name}'," +
            "com_price=${com_price},com_stock=${com_stock},com_type='${com_type}',com_description='${com_description}',time=now() where com_id=${com_id}")
    public int updateCommodity(commodity commodity);

    @Delete("delete from commodity where com_id=#{com_id}")//删除语句根据商品id删除商品
    public int deleteCommodity(int com_id);


    @Update("update commodity set active = 0 where com_id=#{com_id}")
    public int takeOff(int com_id);//下架商品
}
