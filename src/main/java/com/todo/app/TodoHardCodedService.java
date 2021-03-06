package com.todo.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int idCounter = 0;
	
	static {
		todos.add(new Todo(++idCounter, "shrey", "First thing", new Date(), false));
		todos.add(new Todo(++idCounter, "shrey", "Second thing", new Date(), false));
		todos.add(new Todo(++idCounter, "shrey", "Third thing", new Date(), false));
	}
	
	public List<Todo> findAll() {
		return todos;
	}
}
