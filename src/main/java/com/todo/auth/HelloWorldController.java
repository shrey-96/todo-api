package com.todo.auth;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {

	//@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/bean")
	public Bean bean() {
		return new Bean("Test bean");
	}
	
	
	@GetMapping(path="/bean/{name}")
	public Bean path(@PathVariable String name) {
		return new Bean(name);
	}
	
}
