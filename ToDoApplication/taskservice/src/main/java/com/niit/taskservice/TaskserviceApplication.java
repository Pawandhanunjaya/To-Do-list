package com.niit.taskservice;

import com.niit.taskservice.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class TaskserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskserviceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean jwtfilter(){
		// add url intercepting here, by using created filter
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new JwtFilter()); // defined filter works here on added urls
		frb.addUrlPatterns("/myapp/v1/task/*");
		return frb;
	}
}
