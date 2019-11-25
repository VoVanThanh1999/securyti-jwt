package com.example.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class RestApiUserController {
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public String userIndex() {
		return "<h1>User Page</h1>";
	}
	
}
