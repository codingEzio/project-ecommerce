package com.elliot.mall.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.elliot.mall.mapper", "com.elliot.mall.portal.dao"})
public class MyBatisConfig {
}
