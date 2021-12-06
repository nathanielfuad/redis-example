package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repositorys.UserRepository;

@RestController
@RequestMapping("/redis")
public class RedisController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public Map<Object, Object> findAll(){
		
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User findById(@PathVariable("id") int id){
		return userRepository.findUserBydId(id);
	}
	
	
	@PostMapping("/users")
	public User addNewUser(@RequestBody User user) {
		
		user = userRepository.save(user);
		if(user == null) {
			return null;
		}
		
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public Long deleteUser(@PathVariable("id") int id) {
		return userRepository.deleteUserById(id);
	}
	
}
