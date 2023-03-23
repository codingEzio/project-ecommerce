package com.elliot.mall.security.config;

import com.elliot.mall.security.component.*;
import com.elliot.mall.security.util.JWTTokenUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This is a Spring Security configuration class that provides some common beans used for security. It includes beans for password encoding, ignoring URLs, JWT token handling, and access denied/entry point handlers. Additionally, there are conditional beans for dynamic security management, which will be used if a bean named "dynamicSecurityService" is present.
 */
@Configuration
public class CommonSecurityConfig {
	/**
	 * This is a Spring Security bean used for encoding passwords. In this implementation, the BCryptPasswordEncoder is used to encode passwords, which is a popular choice due to its security.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * This is a custom configuration class used to specify a list of URLs that should be ignored by Spring Security. This can be useful for endpoints that should be publicly accessible, such as a login page or a public API.
	 */
	@Bean
	public IgnoreUrlsConfig ignoreUrlsConfig() {
		return new IgnoreUrlsConfig();
	}

	/**
	 * This is a utility class for generating and validating JSON Web Tokens (JWTs). JWTs are a type of token used for authentication and authorization in web applications. The JwtTokenUtil class provides methods for creating JWTs with a secret key, validating JWTs, and extracting information from JWTs.
	 */
	@Bean
	public JWTTokenUtil jwtTokenUtil() {
		return new JWTTokenUtil();
	}

	/**
	 * This is a custom implementation of the AccessDeniedHandler interface used for handling access denied errors. When a user tries to access a resource they are not authorized to access, Spring Security will throw an AccessDeniedException. The RestfulAccessDeniedHandler catches this exception and returns a JSON response with a 403 Forbidden status code.
	 */
	@Bean
	public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
		return new RestfulAccessDeniedHandler();
	}

	/**
	 * This is a custom implementation of the AuthenticationEntryPoint interface used for handling authentication errors. When a user tries to access a protected resource without being authenticated, Spring Security will throw an AuthenticationException. The RestAuthenticationEntryPoint catches this exception and returns a JSON response with a 401 Unauthorized status code.
	 */
	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	/**
	 * This is a custom implementation of the OncePerRequestFilter class used for authenticating requests using JWTs. The filter intercepts incoming requests and extracts the JWT from the request header. If the JWT is valid, the user is authenticated and the request is allowed to proceed. If the JWT is invalid, the user is not authenticated and an error response is returned.
	 */
	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}

	/**
	 * This is a custom implementation of the AccessDecisionManager interface used for handling dynamic security configurations. If there are dynamic security configurations, the DynamicAccessDecisionManager checks if the user has the necessary permissions to access the requested resource.
	 */
	@ConditionalOnBean(name = "dynamicSecurityService")
	@Bean
	public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
		return new DynamicAccessDecisionManager();
	}

	/**
	 * This is a custom implementation of the FilterInvocationSecurityMetadataSource interface used for providing dynamic security configurations to the DynamicAccessDecisionManager. The DynamicSecurityMetadataSource retrieves the dynamic security configurations and provides them to the DynamicAccessDecisionManager.
	 */
	@ConditionalOnBean(name = "dynamicSecurityService")
	@Bean
	public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
		return new DynamicSecurityMetadataSource();
	}

	/**
	 * This is a custom implementation of the AbstractSecurityInterceptor class used for intercepting requests and applying dynamic security configurations. The DynamicSecurityFilter retrieves the dynamic security configurations and applies them to the intercepted requests before they are processed.
	 */
	@ConditionalOnBean(name = "dynamicSecurityService")
	@Bean
	public DynamicSecurityFilter dynamicSecurityFilter() {
		return new DynamicSecurityFilter();
	}
}
