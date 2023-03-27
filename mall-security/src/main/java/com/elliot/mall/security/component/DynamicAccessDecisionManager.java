package com.elliot.mall.security.component;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * This code is an implementation of an AccessDecisionManager which is responsible for making the final decision whether a user should be granted access to a specific resource or not based on their roles/permissions.
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {

	/**
	 * The decide method takes the Authentication object (containing user's authentication details), the resource object, and the list of required ConfigAttributes (roles/permissions) as input. It then checks if the user has the required permission by comparing the user's roles with the required ConfigAttributes. If the user has the permission, the method returns without any exception. Otherwise, it throws an AccessDeniedException.
	 *
	 * @param authentication
	 * @param object
	 * @param configAttributes
	 *
	 * @throws AccessDeniedException
	 * @throws InsufficientAuthenticationException
	 */
	@Override
	public void decide(Authentication authentication, Object object,
	                   Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (CollUtil.isEmpty(configAttributes)) {
			return;
		}

		for (ConfigAttribute configAttribute: configAttributes) {
			String needAuthority = configAttribute.getAttribute();

			for (GrantedAuthority grantedAuthority: authentication.getAuthorities()) {
				if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("Sorry, you are not authorized to access this.");
	}

	/**
	 * The supports methods simply return true to indicate that the implementation supports any type of ConfigAttribute and Class<?>.
	 */
	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

}
