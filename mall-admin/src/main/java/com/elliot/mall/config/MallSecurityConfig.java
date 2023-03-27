package com.elliot.mall.config;

import com.elliot.mall.model.UmsResource;
import com.elliot.mall.security.component.DynamicSecurityService;
import com.elliot.mall.service.UmsAdminService;
import com.elliot.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MallSecurityConfig {

	@Autowired
	private UmsAdminService adminService;
	@Autowired
	private UmsResourceService resourceService;

	/**
	 * Defines the service to be used to load user details for authentication
	 *
	 * @return An instance of UserDetailsService
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		// Get login user information
		return username -> adminService.loadUserByUsername(username);
	}

	/**
	 * Defines the DynamicSecurityService bean for dynamic security configurations
	 *
	 * @return An instance of DynamicSecurityService
	 */
	@Bean
	public DynamicSecurityService dynamicSecurityService() {
		return new DynamicSecurityService() {
			// Returns a map of all resources and their corresponding configurations for access control
			@Override
			public Map<String, ConfigAttribute> loadDataSource() {
				Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();

				List<UmsResource> resourceList = resourceService.listAll();

				for (UmsResource resource: resourceList) {
					// Add each resource and its corresponding configuration to the map
					map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
				}

				return map;
			}
		};
	}
}
