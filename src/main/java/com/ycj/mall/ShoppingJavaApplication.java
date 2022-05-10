package com.ycj.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ycj.mall.mapper")
public class ShoppingJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingJavaApplication.class, args);
    }

}
