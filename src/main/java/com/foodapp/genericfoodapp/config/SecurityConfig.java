package com.foodapp.genericfoodapp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer, WebMvcRegistrations {
	
	private static final String EVERYTHING = "/**";

	@Bean
	public SuperInterceptor superInterceptor() {
		return new SuperInterceptor();
	} 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		List<String> paths =  new ArrayList<String>();
		paths.add("/login");
		paths.add("/user/register");
		registry.addInterceptor(superInterceptor()).addPathPatterns(EVERYTHING).excludePathPatterns(paths);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.cors();
	}
}
