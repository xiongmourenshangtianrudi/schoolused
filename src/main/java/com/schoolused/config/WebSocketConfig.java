package com.schoolused.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;
import java.rmi.server.ServerCloneException;

@Configuration

public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){return new ServerEndpointExporter();}
//    将websockt配置到springboot里
}
