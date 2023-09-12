package com.example.goodWriting.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.goodWriting.domain.user.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/api/board/create/temp");
	}

	//쿠키 허용 allowCredentials
	//allowCredentials 사용시  allowedOrigins 사용불가 따라서 allowedOriginPatterns 사용
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOriginPatterns("*")
			.allowedMethods("GET", "POST")
			.allowCredentials(true)
			.maxAge(3000);
	}
}
