package com.github.eagle;

import com.github.eagle.config.DruidDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;


/**
 * EagleApp
 *
 * @author edwin
 */
@Import(DruidDataSourceConfig.class)
@SpringBootApplication
public class EagleApp {

    public static void main(String[] args) {
        SpringApplication.run(EagleApp.class, args);
    }

}
