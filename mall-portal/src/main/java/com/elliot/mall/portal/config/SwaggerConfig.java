package com.elliot.mall.portal.config;

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
				.apiBasePackage("com.elliot.mall.portal.controller")
				.title("Mall Portal")
				.description("API Documentation for Mall Portal")
				.contactName("Elliot")
				.enableSecurity(false)
				.build();
	}

	@Bean
	public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
		return generateBeanPostProcessor();
	}
}
