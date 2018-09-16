package com.madongfang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
