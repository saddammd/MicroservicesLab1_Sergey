package com.microservice.practical.demo.user.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import com.microservice.practical.demo.user.microservice.service.UserService;
import com.microservice.practical.demo.user.microservice.service.UserServiceImpl;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public UserService getUserService() {
		return new UserServiceImpl();
	}
	
}
