package com.schoolused;

import com.schoolused.Component.WebSocketServe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication

public class schoolusedApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(schoolusedApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        WebSocketServe.setApplicationContext(configurableApplicationContext);

    }
}
