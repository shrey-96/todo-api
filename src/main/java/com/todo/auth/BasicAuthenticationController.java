package com.todo.auth;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BasicAuthenticationController {

	//@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(path="/basicauth")
	public AuthenticationBean helloWorld() {
		return new AuthenticationBean("You are authenticated");
	}
	
	
	@GetMapping(path="/bean/{name}")
	public AuthenticationBean path(@PathVariable String name) {
		return new AuthenticationBean(name);
	}
	
}
