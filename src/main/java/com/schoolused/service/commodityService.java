package com.schoolused.service;

import com.schoolused.entry.commodity;
import com.schoolused.entry.image;
import com.schoolused.until.page;

import java.util.List;

public interface commodityService {

    public List<commodity> findAllCom();

    public page<commodity> findAllCombyId(int page, int size, String type);

    public page<commodity> findAllCombypage(int page, int size);

    public commodity findcomByid(int id);

    public page<commodity> findCommodityByKeywords(int page,int size,String keywords);
    public int addNewItem(commodity commodity);
    public commodity getCommodityInfo(int id);//获取商品信息
    public List<commodity> getCommodityByUserShenhe(int id);//获取用户当前的审核信息
    public int getCom_userid(int com_id);//获取商品userid
    public List<commodity> findCommodityById(int id);

    public int updateCommodityServe(commodity commodity);
    public int addCommodityImage(int id,List<String> urls);//用于保存图片的链接

    public int deleteComByid(int id);//通过id删除商品信息

    public int takeOff(int id);//下架商品
}
