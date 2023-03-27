package com.elliot.mall.config;

import com.elliot.mall.common.config.BaseSwaggerConfig;
import com.elliot.mall.common.domain.SwaggerProperties;
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
				.apiBasePackage("com.elliot.mall.controller")
				.title("Mall Admin")
				.description("API Documentation for Mall Admin")
				.contactName("Elliot")
				.enableSecurity(false)
				.build();
	}

	@Bean
	public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
		return generateBeanPostProcessor();
	}
}
