package com.skilldistillery.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User register(String json) {
		User u = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			u = mapper.readValue(json, User.class);
			u.setPassword(encoder.encode(u.getPassword()));
			u.setEnabled(true);
			u.setRole("standard");
			return userRepo.saveAndFlush(u);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
