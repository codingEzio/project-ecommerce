package com.elliot.mall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.elliot.mall")
public class MallPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallPortalApplication.class, args);
	}

}
