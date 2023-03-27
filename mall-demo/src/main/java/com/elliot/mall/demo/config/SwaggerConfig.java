package com.elliot.mall.demo.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.elliot.mall.common.config.BaseSwaggerConfig;
import com.elliot.mall.common.domain.SwaggerProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

	@Override
	public SwaggerProperties swaggerProperties() {
		return SwaggerProperties.builder()
				.apiBasePackage("com.elliot.mall.demo.controller")
				.title("Mall Demo")
				.description("Documentation for demo code in Mall Demo")
				.contactName("Elliot")
				.enableSecurity(true)
				.build();
	}

	@Bean
	public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
		return generateBeanPostProcessor();
	}

}
