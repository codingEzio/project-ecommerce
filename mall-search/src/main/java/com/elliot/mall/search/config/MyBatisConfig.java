package com.elliot.mall.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.elliot.mall.mapper", "com.elliot.mall.search.dao"})
public class MyBatisConfig {
}
