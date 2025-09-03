package com.ginko.httpforward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HttpForwardApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(HttpForwardApplication.class, args);
    }
}
