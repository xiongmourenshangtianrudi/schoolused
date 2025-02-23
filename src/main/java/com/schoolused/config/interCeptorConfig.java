package com.schoolused.config;

import com.schoolused.controler.interceptor.clientInterCeptor;
import com.schoolused.controler.interceptor.loginInterCeptor;
import com.schoolused.controler.interceptor.roleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class interCeptorConfig implements WebMvcConfigurer {
    @Autowired
    loginInterCeptor loginInterCeptor;

    @Autowired
    roleInterceptor roleInterceptor;

    @Autowired
    clientInterCeptor clientInterCeptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterCeptor).addPathPatterns("/api/User");
        registry.addInterceptor(roleInterceptor).addPathPatterns("/api/User/DeleteUser");
        registry.addInterceptor(roleInterceptor).addPathPatterns("/api/User/DeleteUser/**");
        registry.addInterceptor(roleInterceptor).addPathPatterns("/api/User/updateuser");
        registry.addInterceptor(clientInterCeptor).addPathPatterns("/api/fund/pay");
    }
}
