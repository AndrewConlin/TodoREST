package com.skilldistillery.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.todoapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findOneUserByUsername(String username); 
	public User findByUsername(String username); 
}
