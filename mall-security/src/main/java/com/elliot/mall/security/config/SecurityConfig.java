package com.elliot.mall.security.config;

import com.elliot.mall.security.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * This is a configuration class for Spring Security. It defines a SecurityFilterChain bean that sets up the security for the application. It uses the IgnoreUrlsConfig class to determine which URLs should not be protected and allows access to them. It also sets up the authentication and authorization rules, including handling exceptions, and uses JWT for authentication. It includes a DynamicSecurityFilter to handle dynamic security configurations.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private IgnoreUrlsConfig ignoreUrlsConfig;
	@Autowired
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	@Autowired(required = false)
	private DynamicSecurityService dynamicSecurityService;
	@Autowired(required = false)
	private DynamicSecurityFilter dynamicSecurityFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
				.authorizeRequests();
		//Allow access to unprotected resource paths
		for (String url: ignoreUrlsConfig.getUrls()) {
			registry.antMatchers(url).permitAll();
		}
		//Allow CORS OPTIONS requests
		registry.antMatchers(HttpMethod.OPTIONS)
				.permitAll();
		// Require authentication for all requests
		registry.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				// Disable CSRF and session management
				.and()
				.csrf()
				.disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// Custom access denied and authentication entry point handlers
				.and()
				.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint)
				// Custom JWT filter for authentication
				.and()
				.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

		// Add dynamic security check filter if dynamic security service is provided
		if (dynamicSecurityService != null) {
			registry.and().addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
		}

		return httpSecurity.build();
	}

}
