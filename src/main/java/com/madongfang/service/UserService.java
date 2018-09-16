package com.madongfang.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.ReturnApi;
import com.madongfang.entity.User;
import com.madongfang.exception.HttpUnauthorizedException;
import com.madongfang.repository.UserRepository;

@Service
public class UserService {

	public User login(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (null == user)
		{
			logger.warn("login failed: username={}, password={}", username, password);
			throw new HttpUnauthorizedException(new ReturnApi(-1, "用户名或密码错误"));
		}
		
		return user;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
}
