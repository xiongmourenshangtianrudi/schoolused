package com.schoolused.controler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import com.schoolused.entry.commodity;
import com.schoolused.entry.image;
import com.schoolused.entry.upimage;
import com.schoolused.service.Impl.RegularUserServeImpl;
import com.schoolused.service.Impl.commodityServiceImpl;
import com.schoolused.until.page;
import com.sun.xml.internal.bind.v2.TODO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/commodity")
public class commodityControl {
    @Autowired
    commodityServiceImpl commodityService;
    @Autowired
    RegularUserServeImpl regularUserServe;
    @GetMapping("/{page}/{size}")

    public Result findAllCom(@PathVariable("page") int page,@PathVariable("size") int size){
        System.out.println(page);
        System.out.println(size);
        page<commodity> pager=commodityService.findAllCombypage(page,size);
        return new Result(code.REQUEST_OK,pager,"获取成功");
    }

    @GetMapping("/allcom/{page}/{size}")

    public Result findAllComall(@PathVariable("page") int page,@PathVariable("size") int size){
        System.out.println(page);
        System.out.println(size);
        page<commodity> pager=commodityService.findAllCombypageALL(page,size);
        return new Result(code.REQUEST_OK,pager,"获取成功");
    }
    @GetMapping("/{page}/{size}/{type}")
    public Result findAllComByType(@PathVariable("page") int page,@PathVariable("size")
            int size,@PathVariable("type") String type){
        System.out.println(page);
        System.out.println(size);
        System.out.println(type);
        page<commodity> pager=commodityService.findAllCombyId(page,size,type);
        return new Result(code.REQUEST_OK,pager,"获取成功");
    }
    @GetMapping("/{id}")//通过id去获取商品
    public  Result getCommodityInfo(@PathVariable int id){
        commodity commodity = commodityService.getCommodityInfo(id);
        return new Result(301,commodity,"获取成功");
    }

    @GetMapping("/search/{page}/{size}/{keywords}")//通过关键词获取商品信息
    public Result findComByKeywords(@PathVariable("page") int page,@PathVariable("size")
            int size,@PathVariable("keywords") String keywords){
        page<commodity> pager =commodityService.findCommodityByKeywords(page, size, keywords);
        return new Result(301,pager,"获取成功");
    }

    @GetMapping("/shenhe/{id}")
    public Result getCommodityByIdforShenhe(@PathVariable int id){

        List<commodity> commodities =commodityService.getCommodityByUserShenhe(id);
       if(commodities !=null){
           return new Result(301,commodities,"获取成功");
       }else{
           return new Result(300,"失败");
       }

    }

    @PostMapping("/upload")
    public Result uploadImage(MultipartFile file)throws IOException {//上传商品封面
        String url = regularUserServe.saveAimage(file);
        System.out.println(url);
        return new Result(code.REQUEST_OK,url,"上传成功");
    }

    @PostMapping("/upload1")
    public Dict uploads(MultipartFile file)throws IOException {//上传商品封面
        String url = regularUserServe.saveAimage(file);
        System.out.println(url);
        return Dict.create().set("errno", 0).set("data", CollUtil.newArrayList(Dict.create().set("url", url)));
    }

    @PostMapping("/uploadInfoImage")
    public Result uploadInfoImage(@RequestBody commodity commodity)throws IOException{
        //后面增加事件，保证同时成功与失败
        System.out.println(commodity);
        int num = commodityService.addCommodityImage(commodity.getCom_id(),commodity.getUrls());//将用户上传的图片url地址放入数据库
        int upnum = commodityService.updateCommodityServe(commodity);
        return new Result(code.REQUEST_OK,"上传成功");
    }

    @PostMapping("/newcommodity")
    public Result newCommodity(@RequestBody commodity commodity){

        int data =  commodityService.addNewItem(commodity);
        if(data>0){
            return new Result(301,data,"上传成功");
        }else{
            return new Result(300,"上传失败");
        }

    }

    @GetMapping("/user/{id}")//查看用户上架的商品
    public Result usersCommodity(@PathVariable  int id){
        return new Result(301,commodityService.findCommodityById(id),"获取成功");
    }
    @DeleteMapping("/delete/{id}")
    public Result deleteCommodity(@PathVariable int id){
        return new Result(commodityService.takeOff(id),"删除");
    }
}
