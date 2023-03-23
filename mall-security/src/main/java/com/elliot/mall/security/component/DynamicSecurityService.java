package com.elliot.mall.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * This is an interface named DynamicSecurityService, which declares a single method loadDataSource() that returns a Map object. The purpose of this interface is to provide a contract for implementing classes to load and return dynamic security configurations, which are used by the DynamicSecurityMetadataSource class to determine the access control for incoming requests.
 */
public interface DynamicSecurityService {
	Map<String, ConfigAttribute> loadDataSource();
}
