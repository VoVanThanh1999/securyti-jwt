package com.example.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.service.UserDetailsServiceImlp;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsServiceImlp userDetailsService;
	
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	 @Bean
	 public com.example.security.jwt.JwtAuthenticationFilter jwtAuthenticationFilter() {
	       return new com.example.security.jwt.JwtAuthenticationFilter();
	 }
	 @Bean(BeanIds.AUTHENTICATION_MANAGER)
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	        // Get AuthenticationManager Bean
	        return super.authenticationManagerBean();
	 }
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
	    http.authorizeRequests().antMatchers("/api/login","logout").permitAll();
	    http.authorizeRequests().antMatchers("/api/users").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
	    http.authorizeRequests()
	    .antMatchers("/admin").access("hasRole('ROLE_ADMIN')");	   
	    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	 }

}
