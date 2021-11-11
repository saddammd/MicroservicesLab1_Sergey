package com.microservice.practical.demo.user.microservice.model;

import java.util.List;

public class UserAlbumsDto {
	
	private List<Albums> albums;
	
	

	public UserAlbumsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Albums> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Albums> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "UserAlbumsDto [albums=" + albums + ", getAlbums()=" + getAlbums() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
	

}
