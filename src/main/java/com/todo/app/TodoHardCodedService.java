package com.todo.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static long idCounter = 0;
	
	static {
		todos.add(new Todo(++idCounter, "shrey", "First thing", new Date(), false));
		todos.add(new Todo(++idCounter, "shrey", "Second thing", new Date(), false));
		todos.add(new Todo(++idCounter, "shrey", "Third thing", new Date(), false));
	}
	
	public List<Todo> findAll() {
		return todos;
	}
	
	public Todo save(Todo todo) {
		System.out.println(">> " + todo.getId());
		if(todo.getId() == 0 || todo.getId() == -1) {
			todo.setId(todos.get(todos.size() - 1).getId() + 1);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		
		
		return todo;
	}

	public Todo deleteById(long id)
	{
		Todo todo = findById(id);
		if(todo == null)
			return null;
		
		if(todos.remove(todo))
			return todo;
		
		return null;
		
	}
	
	public Todo findById(long id) {
		
		for(Todo todo:todos) {
			if(todo.getId() == id)
				return todo;
		}
		
		return null;
	}
}
