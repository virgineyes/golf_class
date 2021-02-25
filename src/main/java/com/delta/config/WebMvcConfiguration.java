package com.delta.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author: ACE.CHIU
 * @create: 2021-02-25
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Value("${allowed.origins}")
	private String allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		CorsRegistration corsRegistration = registry.addMapping("/**");
		corsRegistration.allowCredentials(true);
		corsRegistration.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH", "OPTION");
		corsRegistration.allowedOrigins(allowedOrigins.split(","));
	}

}