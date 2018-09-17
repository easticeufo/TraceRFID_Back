package com.madongfang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.ProjectApi;
import com.madongfang.entity.User;
import com.madongfang.service.ProjectService;

@RestController
@RequestMapping(value="/api/projects")
public class ProjectController {

	@GetMapping(params="page")
	public Page<ProjectApi> getProjects(@RequestAttribute User user, @PageableDefault(size=100) Pageable pageable)
	{
		return projectService.getProjects(user, pageable);
	}
	
	@Autowired
	private ProjectService projectService;
}
