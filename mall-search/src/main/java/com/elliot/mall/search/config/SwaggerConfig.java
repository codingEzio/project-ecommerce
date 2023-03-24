package com.elliot.mall.search.config;

import com.macro.mall.common.config.BaseSwaggerConfig;
import com.macro.mall.common.domain.SwaggerProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
	@Override
	public SwaggerProperties swaggerProperties() {
		return SwaggerProperties.builder()
				.apiBasePackage("com.elliot.mall.search.controller")
				.title("Mall Search")
				.description("API Documentation for Mall Search")
				.contactName("Elliot")
				.enableSecurity(false)
				.build();
	}

	@Bean
	public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
		return generateBeanPostProcessor();
	}
}
