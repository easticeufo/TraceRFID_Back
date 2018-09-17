package com.madongfang.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	public Page<Project> findByIdIn(Collection<Integer> projectIds, Pageable pageable);
}
