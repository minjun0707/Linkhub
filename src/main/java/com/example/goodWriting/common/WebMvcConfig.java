package com.example.goodWriting.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.goodWriting.domain.user.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new LoginInterceptor())
		// 	.addPathPatterns("/api/board/create/temp");
	}

	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8080","http://localhost:3000","http://localhost:3003")
			.allowedMethods("GET", "POST")
			.allowCredentials(true)
			.maxAge(3000);
	}
}
