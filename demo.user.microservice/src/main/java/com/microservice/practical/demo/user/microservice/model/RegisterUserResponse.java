package com.microservice.practical.demo.user.microservice.model;

public class RegisterUserResponse {


	private String id;
	private String username;
	private String email;
	public RegisterUserResponse(String id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}
	public RegisterUserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "RegisterUserResponse [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
	
	
}
