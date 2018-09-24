package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.ReturnApi;
import com.madongfang.api.UserApi;
import com.madongfang.entity.User;
import com.madongfang.exception.HttpNotFoundException;
import com.madongfang.exception.HttpUnauthorizedException;
import com.madongfang.repository.UserProjectRepository;
import com.madongfang.repository.UserRepository;

@Service
public class UserService {

	public static UserApi user2Api(User user) {
		return user2Api(user, null);
	}
	
	public static UserApi user2Api(User user, UserApi userApi) {
		if (userApi == null)
		{
			userApi = new UserApi();
		}
		userApi.setId(user.getId());
		userApi.setLevel(user.getLevel());
		userApi.setUsername(user.getUsername());
		return userApi;
	}
	
	public User login(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (null == user)
		{
			logger.warn("login failed: username={}, password={}", username, password);
			throw new HttpUnauthorizedException(new ReturnApi(-1, "用户名或密码错误"));
		}
		
		return user;
	}
	
	public List<UserApi> getUsers() {
		List<UserApi> users = new LinkedList<>();
		for (User user : userRepository.findAll()) {
			users.add(user2Api(user));
		}
		return users;
	}
	
	public UserApi getUser(int userId) {
		User user = userRepository.findOne(userId);
		if (user == null)
		{
			throw new HttpNotFoundException(new ReturnApi(-1, "user not found"));
		}
		
		return user2Api(user);
	}
	
	public UserApi setUser(int userId, UserApi userApi) {
		User user = userRepository.findOne(userId);
		if (user == null)
		{
			throw new HttpNotFoundException(new ReturnApi(-1, "user not found"));
		}
		
		if (userApi.getLevel() != null)
		{
			user.setLevel(userApi.getLevel());
		}
		if (userApi.getPassword() != null)
		{
			user.setPassword(userApi.getPassword());
		}
		if (userApi.getUsername() != null)
		{
			user.setUsername(userApi.getUsername());
		}
		userRepository.save(user);
		
		return user2Api(user, userApi);
	}
	
	public UserApi addUser(UserApi userApi) {
		User user = new User();
		user.setLevel(userApi.getLevel());
		user.setPassword(userApi.getPassword());
		user.setUsername(userApi.getUsername());
		userRepository.save(user);
		
		return UserService.user2Api(user, userApi);
	}
	
	public void deleteUser(int userId) {
		userRepository.delete(userId);
		userProjectRepository.deleteByUserId(userId);
		return;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserProjectRepository userProjectRepository;
}
