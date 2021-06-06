package com.todo.auth;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").permitAll();
//
//		http.cors().and()
//        .csrf().disable()   
//        .authorizeRequests()	
//        .antMatchers(HttpMethod.GET, "/**").permitAll()
//        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     
//        .antMatchers(HttpMethod.PUT, "/**").permitAll()
//        .antMatchers(HttpMethod.DELETE, "/**").permitAll()
//				.anyRequest().authenticated()
//				.and()
//		.httpBasic();
//		
//		System.out.println("\n\nWorking");
//		
//		
//	}
	
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
//        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests((requests) -> 
	        requests
	        .antMatchers(HttpMethod.GET, "/**").permitAll()
	        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest()
	            .authenticated());
	    http.csrf().disable();
	    http.httpBasic();
	    
	    http.cors();
	}
}
