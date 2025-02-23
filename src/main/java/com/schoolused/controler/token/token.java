package com.schoolused.controler.token;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class token {


    //public List<Map<Integer,String> mapArrayList = new ArrayList<>();
    public Map<String,String> map = new HashMap<>();

    public void saveToken(String username,String token){//登录成功保存生成的token
        map.put(username,token);
    }

    public String getToken(String username){//遍历文件中
        return map.get(username);
    }

    public void cleanToken(String username){//清清除token
        map.remove(username);
    }

}
