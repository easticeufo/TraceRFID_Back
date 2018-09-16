package com.madongfang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.UserProject;
import com.madongfang.entity.UserProjectPK;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectPK> {

}
