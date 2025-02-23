package com.schoolused;

import com.schoolused.controler.token.token;
import com.schoolused.mapper.commodityDao;
import com.schoolused.service.Impl.TradingServeImpl;
import com.schoolused.service.Impl.commodityServiceImpl;
import com.schoolused.service.Impl.indexMapserviceImpl;
import com.schoolused.service.Impl.systemTalkServiceImpl;
import com.schoolused.until.jwtuntil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class schoolusedApplicationtest {
    @Autowired
    commodityServiceImpl commodityService;
    //userServiceImpl userService;
    @Autowired
    jwtuntil jwtuntil;

    @Autowired
    token token;
    @Autowired
    commodityDao commodityDao;

    @Autowired
    TradingServeImpl tradingServe;

    @Autowired
    indexMapserviceImpl indexMapservice;

    @Autowired
    systemTalkServiceImpl systemTalkService;

    @Test
    void contextLoads(){
    //System.out.println(userService.findAllUser());
        //jwtuntil.jwt();
        //jwtuntil.parse();
//        List<Integer> x = new ArrayList<>();
//        x.add(4);
//        x.add(5);
//        System.out.println(x);
//        userService.deleteServe(x);

       //System.out.println(commodityService.findAllCombyId(1,2,"家具"));
        System.out.println(commodityService.findCommodityById(1));
    }
    @Test
    void getShoppingCar(){
        System.out.println(tradingServe.getAllShoppingCar(3));
    }

    @Test
    void getOrders(){
        System.out.println(tradingServe.getOrders(2));
    }

    @Test
    void deleteAllMapTest(){
        int x = indexMapservice.deleteAllMap();
        System.out.println(x);
    }

    @Test
    void getmsg(){
        systemTalkService.gettalkmsg(1);
    }

}
