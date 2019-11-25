package com.example.security.jwt;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

@Component
public class LoginRequest {
	
	@NotNull
	private String username;

	@NotNull
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 
	 
}
