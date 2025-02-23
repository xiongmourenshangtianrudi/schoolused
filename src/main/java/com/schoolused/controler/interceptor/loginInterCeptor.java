package com.schoolused.controler.interceptor;

import com.schoolused.controler.token.token;
import com.schoolused.until.jwtuntil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class loginInterCeptor implements HandlerInterceptor {
    @Autowired
    token token;
    @Autowired
    jwtuntil jwtuntil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//       System.out.println(request.getHeader("token"));
//        System.out.println(response.getHeader("token"));
//        System.out.println(request.getHeader("pwd"));
//        String username=jwtuntil.parse(request.getHeader("pwd"),request.getHeader("token")).get("username").toString();
//       if(request.getHeader("token") ==null){
//           System.out.println("无效访问");
//           return true;
//       }else{
//           if(request.getHeader("token").equals(token.getToken(username))){
//
//               return true;
//           }else{
//               return false;
//           }
//
//
//       }
        String token =request.getHeader("token");
        System.out.println(token);
        if(token==null){
            System.out.println("无效访问");
            return true;
        }else {
            String signature = request.getHeader("pwd");
            System.out.println(signature);
            Claims claims=jwtuntil.parse(signature,token);
            if(token.equals(this.token.getToken(claims.get("username").toString()))){
                System.out.println("有效访问");
                return true;
            }else{
                System.out.println("token被篡改");
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
