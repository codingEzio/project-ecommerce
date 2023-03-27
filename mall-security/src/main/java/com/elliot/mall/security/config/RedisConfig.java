package com.elliot.mall.security.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import com.elliot.mall.common.config.BaseRedisConfig;

/**
 * This is a configuration class for Redis caching in the application. It is annotated with @EnableCaching to enable caching support in the application.
 * <p>
 * It also extends the BaseRedisConfig class which contains the basic Redis configuration for the application, including the Redis host, port, and password. By extending this class, the RedisConfig class inherits these properties and can be used to configure caching in the application using Redis as the cache provider.
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {
}
