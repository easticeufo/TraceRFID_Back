package com.madongfang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.LoginApi;
import com.madongfang.api.ReturnApi;
import com.madongfang.entity.User;
import com.madongfang.service.UserService;

@RestController
@RequestMapping(value="/api/login")
public class LoginController {
	
	@PostMapping
	public ReturnApi login(HttpSession session, @RequestBody LoginApi loginApi) 
	{	
		User user = userService.login(loginApi.getUsername(), loginApi.getPassword());
		session.setAttribute("user", user);
		return new ReturnApi(0, "OK");
	}
	
	@Autowired
	private UserService userService;
}
