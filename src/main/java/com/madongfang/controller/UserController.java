package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.UserApi;
import com.madongfang.service.UserService;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

	@GetMapping
	public List<UserApi> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping
	public UserApi addUser(@RequestBody UserApi userApi)
	{
		return userService.addUser(userApi);
	}
	
	@GetMapping(value="/{userId}")
	public UserApi getUser(@PathVariable int userId)
	{
		return userService.getUser(userId);
	}
	
	@PutMapping(value="/{userId}")
	public UserApi setUser(@PathVariable int userId, @RequestBody UserApi userApi)
	{
		return userService.setUser(userId, userApi);
	}
	
	@DeleteMapping(value="/{userId}")
	public void deleteUser(@PathVariable int userId)
	{
		userService.deleteUser(userId);
		return;
	}
	
	@Autowired
	private UserService userService;
}
