package com.elliot.mall.common.config;

import com.elliot.mall.common.domain.SwaggerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This file defines the base configuration for Swagger, a tool used for generating API
 * documentation. The configuration sets API information, security settings, and handler
 * mappings.
 * - The createRestApi() method creates a Docket instance, which is the main configuration object for Swagger.
 * - The apiInfo() method creates an ApiInfo object with API metadata, such as the title, description, and version.
 * - The securitySchemes() and securityContexts() methods define the API key authentication scheme and context.
 * - The generateBeanPostProcessor() method creates a BeanPostProcessor instance, which customizes Springfox handler mappings by filtering out null pattern parsers.
 * In analogy,
 * this configuration file is like a blueprint for constructing a building. It specifies the necessary
 * details such as building dimensions, materials, and security measures.
 * - The createRestApi() method is like the architectural design, which outlines the main structure of the building.
 * - The apiInfo(), securitySchemes(), and securityContexts() methods are like the metadata, locks, and security cameras that protect the building.
 * - The generateBeanPostProcessor() method is like the construction manager, which oversees the building process and ensures that the plans are followed correctly.
 * Overall, this file is essential for building a reliable and secure API documentation system.
 */
public abstract class BaseSwaggerConfig {

	@Bean
	public Docket createRestApi() {
		SwaggerProperties swaggerProperties = swaggerProperties();
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo(swaggerProperties))
				.select()
				.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getApiBasePackage()))
				.paths(PathSelectors.any())
				.build();
		if (swaggerProperties.isEnableSecurity()) {
			docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
		}
		return docket;
	}

	private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
		return new ApiInfoBuilder()
				.title(swaggerProperties.getTitle())
				.description(swaggerProperties.getDescription())
				.contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
				.version(swaggerProperties.getVersion())
				.build();
	}

	private List<SecurityScheme> securitySchemes() {
		List<SecurityScheme> result = new ArrayList<>();

		ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
		result.add(apiKey);

		return result;
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> result = new ArrayList<>();
		result.add(getContextByPath("/*/.*"));

		return result;
	}

	private SecurityContext getContextByPath(String pathRegex) {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(pathRegex))
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		List<SecurityReference> result = new ArrayList<>();

		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;

		result.add(new SecurityReference("Authorization", authorizationScopes));

		return result;
	}

	public BeanPostProcessor generateBeanPostProcessor() {
		return new BeanPostProcessor() {

			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
					customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
				}
				return bean;
			}

			private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
				List<T> copy = mappings.stream()
						.filter(mapping -> mapping.getPatternParser() == null)
						.collect(Collectors.toList());
				mappings.clear();
				mappings.addAll(copy);
			}

			@SuppressWarnings("unchecked")
			private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
				try {
					Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
					field.setAccessible(true);

					return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new IllegalStateException(e);
				}
			}
		};
	}

	public abstract SwaggerProperties swaggerProperties();
}
