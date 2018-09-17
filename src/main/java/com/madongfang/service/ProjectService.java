package com.madongfang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.madongfang.api.ProjectApi;
import com.madongfang.entity.Project;
import com.madongfang.entity.User;
import com.madongfang.entity.UserProject;
import com.madongfang.repository.ProjectRepository;
import com.madongfang.repository.UserProjectRepository;

@Service
public class ProjectService {

	public static ProjectApi project2Api(Project project) {
		ProjectApi projectApi =  new ProjectApi();
		projectApi.setId(project.getId());
		projectApi.setConstructor(project.getConstructor());
		projectApi.setName(project.getName());
		projectApi.setSupervisor(project.getSupervisor());
		return projectApi;
	}
	
	public Page<ProjectApi> getProjects(User user, Pageable pageable) {
		Page<Project> projects = null;
		if (user.getLevel() <= 1)
		{
			projects = projectRepository.findAll(pageable);
		}
		else
		{
			List<UserProject> userProjects = userProjectRepository.findByUserId(user.getId());
			List<Integer> projectIds = new ArrayList<>(userProjects.size());
			for (UserProject userProject : userProjects) {
				projectIds.add(userProject.getProjectId());
			}
			projects = projectRepository.findByIdIn(projectIds, pageable);
		}
		
		return projects.map(new Converter<Project, ProjectApi>() {
			@Override
			public ProjectApi convert(Project project) {
				return project2Api(project);
			}
		});
	}
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserProjectRepository userProjectRepository;
}
