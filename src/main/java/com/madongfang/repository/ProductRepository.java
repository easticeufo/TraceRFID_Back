package com.madongfang.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findByProjectId(int projectId);
	
	public Page<Product> findByProjectIdIn(Collection<Integer> projectIds, Pageable pageable);
}
