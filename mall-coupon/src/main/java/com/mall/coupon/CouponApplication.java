package com.mall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.mall.coupon.dao")
// 服务注册发现
@EnableDiscoveryClient
public class CouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }

}
