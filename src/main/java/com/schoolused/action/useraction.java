package com.schoolused.action;

import com.schoolused.entry.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class useraction {
    @Pointcut("execution(* com.schoolused.service.Impl.userServiceImpl.loginService(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public String removeRpaceso(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{//去掉空格
        Object[] info =proceedingJoinPoint.getArgs();
        User info1 = (User)info[0];
        info1.setUserName(info1.getUserName().trim());
        info1.setUserPwd(info1.getUserPwd().trim());
        System.out.println(info1.toString());
        info[0]=(Object)info1;
        String rs = (String) proceedingJoinPoint.proceed(info);//执行原始方法
        return rs;


    }
}
