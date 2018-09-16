package com.madongfang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
