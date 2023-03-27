package com.elliot.mall.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.elliot.mall.mapper")
public class MyBatisConfig {
}
