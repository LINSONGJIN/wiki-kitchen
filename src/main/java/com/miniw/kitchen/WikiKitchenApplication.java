package com.miniw.kitchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.miniw.kitchen.dao"})
public class WikiKitchenApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikiKitchenApplication.class, args);
    }

}
