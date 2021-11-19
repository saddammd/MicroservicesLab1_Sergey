//package com.microservice.practical.demo.user.microservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import feign.RequestInterceptor;
//
//public class FeignConfig {  
//	
//	//HttpServletRequest request;
//
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate -> {
//            requestTemplate.header("Content-Type", "application/json");
//            requestTemplate.header("Accept", "application/json");
//            requestTemplate.header("Authorization", getHeader());
//            
//         };
//    }
//    
//    private String getHeader() {
//    	
//    	String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
//    	return token;
//    }
//}
