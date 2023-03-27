package com.elliot.mall.portal.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * In real-world terms, this configuration is similar to a chef setting a particular spice level for a dish. The chef may choose to use a particular spice or seasoning to enhance the flavor of the dish, but they may also decide to exclude certain ingredients or flavors to achieve the desired taste. Similarly, this configuration defines a specific rule for how JSON data should be serialized, excluding any null values to create a more streamlined and organized output.
 */
@Configuration
public class JacksonConfig {
	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();

		// Configure the mapper to serialize objects based on the provided rule.
		// Include.Include.ALWAYS is the default setting.
		// Include.NON_DEFAULT ignores properties with default values.
		// Include.NON_EMPTY ignores properties that are empty or null, and the resulting JSON does not include those properties.
		// Include.NON_NULL ignores properties that are null.
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return objectMapper;
	}
}
