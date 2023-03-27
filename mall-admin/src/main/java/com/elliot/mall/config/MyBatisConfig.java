package com.elliot.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.elliot.mall.mapper", "com.elliot.mall.dao"})
public class MyBatisConfig {
}
