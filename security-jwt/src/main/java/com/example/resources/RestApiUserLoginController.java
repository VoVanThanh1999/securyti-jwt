package com.example.resources;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.model.CustomUserDetails;
import com.example.security.jwt.JwtTokenProvider;
import com.example.security.jwt.LoginRequest;
import com.example.security.jwt.LoginResponse;

@RestController
@RequestMapping("/api")
public class RestApiUserLoginController {
	@Autowired(required = false)
    AuthenticationManager authenticationManager;;
	@Autowired
    private JwtTokenProvider tokenProvider;
	
	@PostMapping("/login")
	public LoginResponse authenticateUser( @RequestBody LoginRequest loginRequest) {

	     try {
	    	   // Xác thực từ username và password.
		        Authentication authentication = authenticationManager.authenticate(
		                new UsernamePasswordAuthenticationToken(
		                        loginRequest.getUsername(),
		                        loginRequest.getPassword()
		                )
		        );
		        // Nếu không xảy ra exception tức là thông tin hợp lệ
		        // Set thông tin authentication vào Security Context
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        // Trả về jwt cho người dùng.
		        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		        return new LoginResponse(jwt);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	 return null;   
	}
	
}
