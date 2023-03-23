package com.elliot.mall.security.aspect;

import com.elliot.mall.security.annotation.CacheException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * This is an aspect in a Spring Boot application that intercepts method calls to
 * classes that end with "CacheService" in order to provide caching functionality.
 * If the method throws a CacheException, it is re-thrown to the caller, otherwise
 * the exception is logged. All in all, unless it's a CacheException (the functionality
 * we use Redis for), it would only be logged (if Redis itself is down, it shall not
 * interfere or even interrupt/stop our business logic).
 */
@Aspect
@Component
@Order(2)
public class RedisCacheAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

	@Pointcut("execution(public * com.macro.mall.portal.service.*CacheService.*(..)) || execution(public * com.macro.mall.service.*CacheService.*(..))")
	public void cacheAspect() {
	}

	@Around("cacheAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		Object result = null;

		try {
			result = joinPoint.proceed();
		} catch (Throwable throwable) {
			if (method.isAnnotationPresent(CacheException.class)) {
				throw throwable;
			} else {
				LOGGER.error(throwable.getMessage());
			}
		}
		return result;
	}
}
