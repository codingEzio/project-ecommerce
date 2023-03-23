package com.elliot.mall.common.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.elliot.mall.common.service.RedisService;
import com.elliot.mall.common.service.impl.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


public class BaseRedisConfig {
	/**
	 * This code defines a Spring bean that creates a RedisTemplate object, which is a
	 * type of object used for communication with a Redis data store. The RedisTemplate
	 * object provides methods for interacting with Redis keys and values and is
	 * configured with a connection factory and serializers for key and value objects.
	 * The redisTemplate() method takes a RedisConnectionFactory object as an argument,
	 * which it uses to set up a RedisTemplate instance. It also creates serializers for
	 * the key and value objects, sets the serializers and the connection factory on the
	 * template, and initializes the template.
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisSerializer<Object> serializer = redisSerializer();
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

		redisTemplate.setConnectionFactory(factory);

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(serializer);
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(serializer);

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

	/**
	 * This code creates a Spring bean for a RedisService object by instantiating a
	 * RedisServiceImpl instance using the default constructor. The resulting bean can be
	 * used by other components in the application to interact with Redis data store.
	 */
	@Bean
	public RedisService redisService() {
		return new RedisServiceImpl();
	}

	/**
	 * This code defines a bean for a Redis serializer that can serialize and deserialize Java
	 * objects to and from JSON format using the Jackson library. It configures the Jackson
	 * ObjectMapper with certain visibility rules and type handling options before setting
	 * it on the serializer object. The resulting bean can then be used by other components
	 * in the application to interact with Redis.
	 */
	@Bean
	public RedisSerializer<Object> redisSerializer () {
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(objectMapper);

		return serializer;
	}

	/**
	 * This code creates a Spring bean that configures a Redis cache manager for caching
	 * data in Redis. The RedisCacheManager manages caching operations by keeping track
	 * of cached data and retrieving it from the cache when needed. The configuration
	 * specifies how cache entries should be serialized and how long they should be cached.
	 */
	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
		RedisCacheWriter writer = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
		RedisCacheConfiguration config = RedisCacheConfiguration
				.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext
						.SerializationPair
						.fromSerializer(redisSerializer())
				)
				.entryTtl(Duration.ofDays(1));

		return new RedisCacheManager(writer, config);
	}
}
