package com.microservice.practical.demo.user.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfServer {

	@Autowired
	private Environment env;
	
	@GetMapping("/name")
	public String getname() {
		
	 return env.getProperty("name.user");
	}
}
