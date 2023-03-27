package com.elliot.mall.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a configuration class that defines a list of URLs to ignore for security purposes. It uses Lombok to generate getter and setter methods for the urls field, which is a list of strings that contain the URLs to ignore. The @ConfigurationProperties annotation specifies that the values for the urls field should be read from the secure.ignored prefix in the application properties file. The @Component annotation marks this class as a Spring component so that it can be autowired into other classes.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
	private List<String> urls = new ArrayList<>();
}
