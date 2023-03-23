package com.elliot.mall.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is a custom annotation in Java that can be used to mark methods that can throw
 * cache exceptions. It is used to indicate that a particular method may throw an
 * exception related to caching and needs to be handled appropriately. The annotation
 * is used by other classes, such as the RedisCacheAspect class, to identify methods
 * that should have exception handling logic.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
