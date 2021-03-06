package com.microservice.practical.demo.user.microservice.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.practical.demo.user.microservice.model.Albums;

@FeignClient(name="albums-ws"
//,configuration = {FeignConfig.class}
)
public interface AlbumsClient {
	
	@GetMapping("/users/${id}/albums")
	 public List<Albums> getAlbums(@PathVariable String id);

}
