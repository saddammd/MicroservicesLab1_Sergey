package com.microservice.practical.demo.user.microservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.practical.demo.user.microservice.config.AlbumsClient;
import com.microservice.practical.demo.user.microservice.model.Albums;
import com.microservice.practical.demo.user.microservice.model.UserAlbumsDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class PhotoOrder_Controller {
	
	@Autowired
	private AlbumsClient albumsClient;
		

	@CircuitBreaker(name = "cb-instanceA" ,fallbackMethod = "orderAlbumsFallback"
			)
	@GetMapping(value = "/order/{userid}", 
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Albums> getOrder(@PathVariable String userid){
		
		List<Albums> albums = albumsClient.getAlbums(userid);
		UserAlbumsDto userAlbumsDto = new UserAlbumsDto();
		userAlbumsDto.setAlbums(albums);
		return userAlbumsDto.getAlbums();
	}
	
	public List<Albums> orderAlbumsFallback(String userid, RuntimeException e)
	{
		
		Albums albums = new Albums();
		ArrayList<Albums> albumsList = new ArrayList<>();
		albums.setAlbumId("null");
		albums.setDescription("Service Not Available");
		albums.setName(userid);
		albums.setName("null");
		albumsList.add(albums);
		UserAlbumsDto userAlbumsDto = new UserAlbumsDto();
		userAlbumsDto.setAlbums(albumsList);
		return userAlbumsDto.getAlbums();
		
	}
}
