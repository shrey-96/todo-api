package com.todo.auth;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").permitAll();

		http
        .csrf().disable()   
        .authorizeRequests()	
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     
				.anyRequest().authenticated()
				.and()
		.httpBasic();
		
		System.out.println("\n\nWorking");
		
		
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http.authorizeRequests((requests) -> 
//	        requests
//	        .antMatchers(HttpMethod.GET, "/**").permitAll()
//	        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest()
//	            .authenticated());
//	    http.csrf().disable();
//	    http.httpBasic();
//	    
//	    http.cors();
//	}
}
