package com.skilldistillery.todoapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.todoapp.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	public List<Todo> findByUserUsername(String username);
}
