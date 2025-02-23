package com.schoolused.controler.interceptor;

import com.schoolused.until.jwtuntil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class roleInterceptor implements HandlerInterceptor {
    @Autowired
    jwtuntil jwtuntil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String token =request.getHeader("token");
    if(token==null){
        return true;
    }else {
        String signature = request.getHeader("pwd");
        //System.out.println(signature);
        Claims claims=jwtuntil.parse(signature,token);
        System.out.println(claims.get("role"));
        if (jwtuntil.checkRole(claims, "root")) {
            System.out.println("通过");
            return true;
        } else {
            System.out.println("拒绝");
            return false;
        }
    }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
