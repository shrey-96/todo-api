package com.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	
	@DeleteMapping("/users/{username}/todos/{todo_id}")
	public ResponseEntity<Void> deleteTodoItem(@PathVariable String username, @PathVariable long todo_id) {
		Todo todo = todoService.deleteById(todo_id);
		
		if(todo == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(null);
	}
}
