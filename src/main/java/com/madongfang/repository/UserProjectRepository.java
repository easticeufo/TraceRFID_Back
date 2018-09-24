package com.madongfang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.madongfang.entity.UserProject;
import com.madongfang.entity.UserProjectPK;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectPK> {

	public List<UserProject> findByUserId(int userId);
	
	@Query("select up.projectId from UserProject up where up.userId = ?1")
	public List<Integer> findProjectIdsByUserId(int userId);
	
	@Modifying
	@Query("delete from UserProject up where up.userId=?1")
	@Transactional
	public void deleteByUserId(int userId);
}
