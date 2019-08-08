package com.ddbuy.ddbuycommonservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("com.ddbuy.mapper")
public class DdbuyCommonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdbuyCommonServiceApplication.class, args);
    }

}
