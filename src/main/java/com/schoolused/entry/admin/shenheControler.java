package com.schoolused.entry.admin;


import com.schoolused.controler.Result;
import com.schoolused.entry.commodity;
import com.schoolused.until.shenHeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shenhe")
public class shenheControler {

    @Autowired
    shenHeList shenhe;

    @GetMapping("/getlist")
    public Result getlist(){
        List<commodity> shenhes = shenhe.getShenhelist();
        return new Result(301,shenhes,"获取成功");
    }


    @GetMapping("/getshenheing")
    public Result getShenheing(){
        List<commodity> shenheing = shenhe.getShenheing();
        return new Result(301,shenheing,"获取成功");
    }

    @GetMapping("/updatelist/{id}")
    public Result updatelist(@PathVariable int id){
        int x = shenhe.updateList(id);
        if(x ==1){
            return new Result(301,"更新成功");
        }else{
            return new Result(300,"更新失败");
        }
    }

    @PostMapping("/shenheResult")
    public Result shenHeResult(@RequestBody Map<String, Object> jsonMap){
        System.out.println(jsonMap.toString());
        Integer id = (Integer)jsonMap.get("id");
        Integer state = (Integer) jsonMap.get("state");
        int res = shenhe.shenHeControl(id,state);
        if(res ==1){
            return new Result(301,"审核完成");
        }else{
            return new Result(300,"审核失败，结果未保存");
        }

    }
}
