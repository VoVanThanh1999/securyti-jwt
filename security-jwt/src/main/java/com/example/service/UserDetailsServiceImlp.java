package com.example.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.model.CustomUserDetails;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserDetailsServiceImlp implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			CustomUserDetails customUserDetails = new CustomUserDetails(user);
			return customUserDetails;
		}
		return null;
	}
	
	@Transactional
	public UserDetails loadUserById(int id) throws UsernameNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			CustomUserDetails customUserDetails = new CustomUserDetails(user);
			return customUserDetails;
		}
		return null;
	}
}
