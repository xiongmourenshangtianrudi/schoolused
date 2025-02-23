package com.schoolused.service.Impl;

import com.schoolused.entry.commodity;
import com.schoolused.entry.image;
import com.schoolused.mapper.commodityDao;
import com.schoolused.service.commodityService;
import com.schoolused.until.page;
import com.schoolused.until.shenHeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class commodityServiceImpl implements commodityService {
    public List<image> images = new ArrayList<>();
    @Autowired
    commodityDao commodityDao;

    @Override
    public List<commodity> findAllCom() {
        return commodityDao.findAllcom();
    }

    @Override
    public page<commodity> findAllCombyId(int page, int size, String type) {//通过类型查询商品
        Map<String ,Object> params = new HashMap<>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        params.put("type",type);
        List<commodity> commodities = commodityDao.findAllcombyId(params);
        page<commodity> pager = new page<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setData(commodities);
        pager.setTotal(commodityDao.findAllcomByIdCount(type));
        return pager;
    }

    @Override
    public page<commodity> findAllCombypage(int page, int size) {//查询所有在售商品
        Map<String ,Object> params = new HashMap<>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        List<commodity> commodities = commodityDao.findAllcombypage(params);

        page<commodity> pager = new page<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setData(commodities);
        pager.setTotal(commodityDao.findAllcomCount());
        return pager;
    }

    @Override
    public commodity findcomByid(int id) {//通过商品id查询商品

        commodity commodity = commodityDao.findCommodityid(id);

        return commodity;
    }


    public page<commodity> findAllCombypageALL(int page, int size) {//查询所有商品
        Map<String ,Object> param = new HashMap<>();
        param.put("page",(page-1)*size);
        param.put("size",size);
        List<commodity> commodities = commodityDao.findAllcombypageAll(param);
        page<commodity> pager = new page<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setData(commodities);
        pager.setTotal(commodityDao.findAllcomCount());
        return pager;
    }

    @Override
    public page<commodity> findCommodityByKeywords(int page, int size, String keywords) {//搜索出结果并分页显示
        String keyword =keywords.trim();
        Map<String ,Object> params = new HashMap<>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        params.put("keyword",keyword);
        List<commodity> res = commodityDao.searchCombypage(params);
        page<commodity> pager = new page<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setData(res);
        pager.setTotal(commodityDao.findAllcomCount());
        return pager;
    }

    @Override
    public int addNewItem(commodity commodity) {


        return commodityDao.newItem(commodity);
    }

    @Override
    public commodity getCommodityInfo(int id) {//获取商品信息
        commodity commodity=null;
        if(id>=0){
            if(commodityDao.findcommodityByid(id)!=null){
                commodity = commodityDao.findcommodityByid(id);
            }
        }else{
            return null;
        }
        return commodity;
    }

    @Override
    public List<commodity> getCommodityByUserShenhe(int id) {
        List<commodity> commodities = commodityDao.findCommodityByUserIdShenhe(id);

        return commodities;
    }

    @Override
    public List<commodity> findCommodityById(int id) {
        List<commodity> commodities = commodityDao.findCommodityByUserId(id);
        return commodities;
    }

    @Override
    public int getCom_userid(int com_id) {
        System.out.println(com_id);
        int userid = commodityDao.getCom_userid(com_id);//获取该商品的出售用户
        return userid;
    }

    @Override
    public int updateCommodityServe(commodity commodity) {
        int num=0;
        if(commodity!=null){
           num =  commodityDao.updateCommodity(commodity);
        }

        return num;
    }

    @Override
    public int addCommodityImage(int id ,List<String> urls) {//将用户上传的图片的链接直接放入数据库
        int i;
        if(urls!=null){
            for(i=0;i<urls.size();i++){
                System.out.println("上传成功1"+i);
                commodityDao.addCommodityimge(id,urls.get(i));
            }
            return i;
        }else{
            return 0;
        }


    }

    @Override
    public int deleteComByid(int id) {//根据商品id删除商品，一般不进行调用
        int x = commodityDao.deleteCommodity(id);
        if(x>=0){
            return 301;
        }else{
            return 300;
        }

    }

    @Override
    public int takeOff(int id) {
        int x = commodityDao.takeOff(id);
        if(x>=0){
            return 301;
        }else{
            return 300;
        }
    }
    //    //生成一个图片url的list列表
//    public void addUrlList(image image){
//        this.images.add(image);
//        System.out.println(images.toString());
//    }

}
