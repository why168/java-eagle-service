package com.github.eagle.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EagleApp {

    public static void main(String[] args) {
        SpringApplication.run(EagleApp.class, args);
    }

}