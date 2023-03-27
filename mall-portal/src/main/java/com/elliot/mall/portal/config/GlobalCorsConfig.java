package com.elliot.mall.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class for global CORS settings in a Spring backend application.
 */
@Configuration
public class GlobalCorsConfig {

	/**
	 * Defines a CORS filter bean that allows Cross-Origin Resource Sharing (CORS) requests
	 * from any domain with unrestricted access.
	 *
	 * @return The newly created CorsFilter instance.
	 */
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();

		// Allow requests from any domain by setting the allowed origin to "*".
		config.addAllowedOriginPattern("*");

		// Allow credentials to be sent with the request.
		config.setAllowCredentials(true);

		// Allow requests to use any header.
		config.addAllowedHeader("*");

		// Allow requests to use any HTTP method.
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		// Register the global CORS configuration for all endpoints.
		source.registerCorsConfiguration("/**", config);

		// Create and return a new CorsFilter instance that applies the global CORS configuration.
		return new CorsFilter(source);
	}
}
