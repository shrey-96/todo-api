package com.todo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.temp.auth.AuthenticationBean;
import com.todo.app.Todo;
import com.todo.app.TodoJpaRepository;
import com.todo.app.TodoHardCodedService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoJPAResource {

	@Autowired
	private TodoHardCodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	
	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoJpaRepository.findByUsername(username);
		//return todoService.findAll();
	}
	
	@GetMapping("/jpa/test")
	public void test() {
		System.out.println("YOu've hit this endpoint");
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable Long id) {
		System.out.println(">> Found: " + todoJpaRepository.findById(id).get());
		return todoJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}
	
	@DeleteMapping("/jpa/users/{username}/todos/{todo_id}")
	public ResponseEntity<Void> deleteTodoItem(
			@PathVariable String username, @PathVariable long todo_id) {
		
		todoJpaRepository.deleteById(todo_id);
		//Todo todo = todoService.deleteById(todo_id);
		
		//return ResponseEntity.notFound().build();
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/jpa/users/{username}/todos/{todo_id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String username, 
			@PathVariable long todo_id, 
			@RequestBody Todo todo) { 
		
		todo.setUsername(username);
		Todo todoUpdated = todoJpaRepository.save(todo);
		//Todo todoUpdated = todoService.save(todo);
		return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);
		
	}
	
	//@CrossOrigin(origins="http://127.0.0.1:4200")
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username,
			@RequestBody Todo todo) { 
		
		todo.setUsername(username);
		//Todo createdTodo = todoService.save(todo);
		Todo createdTodo = todoJpaRepository.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		

		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping(path="/jpa/bean/{name}")
	public AuthenticationBean path(@PathVariable String name) {
		return new AuthenticationBean(name);
	}
}
