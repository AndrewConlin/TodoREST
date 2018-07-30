package com.skilldistillery.todoapp.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.repositories.TodoRepository;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Set<Todo> index(String username) {
		User u = userRepo.findOneUserByUsername(username);
		return u.getTodos();
	}

	@Override
	public Todo show(String username, int tid) {
		Todo todo = null;
		try {
			todo = todoRepo.findById(tid).get();
			
			if(!todo.getUser().getUsername().equals(username)) {
				return null;
			}
			
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
		
		return  todo;
	}

	@Override
	public Todo create(String username, Todo todo) {
		User u = userRepo.findOneUserByUsername(username);		
		todo.setUser(u);
		return todoRepo.saveAndFlush(todo);
	}

	@Override
	public Todo update(String username, int tid, Todo todo) {
		User u = userRepo.findOneUserByUsername(username);		
		
		todo.setUser(u);
		todo.setId(tid);
		return todoRepo.saveAndFlush(todo);
	}

	@Override
	public void destroy(String username, int tid) {
		Todo todo = todoRepo.findById(tid).get();
		if(todo.getUser().getUsername().equals(username)) {
			todoRepo.delete(todo);
		}
	}

}
