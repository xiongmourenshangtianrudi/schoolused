package com.schoolused.controler.interceptor;

import com.schoolused.controler.token.token;
import com.schoolused.entry.RegularUsers;
import com.schoolused.mapper.RegularUsersDao;
import com.schoolused.until.jwtuntil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class clientInterCeptor implements HandlerInterceptor { //拦截器
    @Autowired
    token token;
    @Autowired
    jwtuntil jwtuntil;

    @Autowired
    RegularUsersDao regularUsersDao;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        String Token = request.getHeader("token");

        String key = request.getHeader("pwd");//获取密钥
        Claims claims = jwtuntil.parse(key, Token);
        String username = claims.get("username").toString();
        System.out.println(token.getToken(username));
        if (token.getToken(username).equals(Token)) {
            RegularUsers regularUsers = regularUsersDao.findUsersByuserName(username);

            if (regularUsers.getUserPwd().equals(key)) { //判断两个数值

                return true;
            }else{
                return false;
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
