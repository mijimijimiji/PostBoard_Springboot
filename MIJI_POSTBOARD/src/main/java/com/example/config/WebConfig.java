package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public WebConfig() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyHandlerInterceptor()) //인터셉터 처리 할 클래스
		.addPathPatterns("/login"); //처리주소
	}
	
	@Bean("jsonView")
	MappingJackson2JsonView jsonView() {
		//ckeditor는 retrun 값으로 uploaded와 url값을 json형식으로 리턴할 수 있게 하기 위함
		return new MappingJackson2JsonView();
	}

}
