package com.skilldistillery.todoapp.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.services.TodoService;

@RestController
@RequestMapping(path="api")
@CrossOrigin({"*", "http://localhost:4200"})
public class TodoController {
	
	@Autowired
	private TodoService todoService;
		
	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	 //    GET todos
	@RequestMapping(path="todos", method=RequestMethod.GET)
	public Set<Todo> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return todoService.index(principal.getName());
	}
	
	 //    GET todos/{tid}
	 @RequestMapping(path="todos/{tid}", method=RequestMethod.GET)
	 public Todo show(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, Principal principal){
		 return todoService.show(principal.getName(), tid);
	 }

	 //    POST todos
	 @RequestMapping(path="todos", method=RequestMethod.POST)
	 public Todo create(HttpServletRequest req, HttpServletResponse res, @RequestBody Todo newTodo, Principal principal){
		 return todoService.create(principal.getName(), newTodo);
	 }

	 //    PUT todos/{tid}
	 @RequestMapping(path="todos/{tid}", method=RequestMethod.PUT)
	 public Todo update(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, @RequestBody Todo newTodo, Principal principal){
		 return todoService.update(principal.getName(), tid, newTodo);
	 }

	 //    DELETE todos/{tid}
	 @RequestMapping(path="todos/{tid}", method=RequestMethod.DELETE)
	 public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, Principal principal){
		 todoService.destroy(principal.getName(), tid);
	 }
}
