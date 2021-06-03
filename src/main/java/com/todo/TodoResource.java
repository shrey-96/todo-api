package com.todo;

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

import com.todo.app.Todo;
import com.todo.app.TodoHardCodedService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoResource {

	@Autowired
	private TodoHardCodedService todoService;
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoService.findAll();
	}
	
	@GetMapping("/test")
	public void test() {
		System.out.println("YOu've hit this endpoint");
	}
	
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoService.findById(id);
	}
	
	@DeleteMapping("/users/{username}/todos/{todo_id}")
	public ResponseEntity<Void> deleteTodoItem(
			@PathVariable String username, @PathVariable long todo_id) {
		Todo todo = todoService.deleteById(todo_id);
		
		if(todo == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(null);
	}
	
	@PutMapping("/users/{username}/todos/{todo_id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String username, 
			@PathVariable long todo_id, 
			@RequestBody Todo todo) { 
		Todo todoUpdated = todoService.save(todo);
		System.out.println("here");
		return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);
		
	}
	
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username,
			@RequestBody Todo todo) { 
		
		Todo createdTodo = todoService.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		

		return ResponseEntity.created(uri).build();
		
	}
}
