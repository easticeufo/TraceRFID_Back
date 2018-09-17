package com.madongfang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findByProjectId(int projectId);
}
