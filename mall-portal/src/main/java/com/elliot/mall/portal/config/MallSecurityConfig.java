package com.elliot.mall.portal.config;

import com.elliot.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class MallSecurityConfig {

	@Autowired
	private UmsMemberService memberService;

	/**
	 * Defines the service to be used to load user details for authentication
	 *
	 * @return An instance of UserDetailsService
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		// Get login user information
		return username -> memberService.loadUserByUsername(username);
	}
}
