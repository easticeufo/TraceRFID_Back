package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.ProductApi;
import com.madongfang.entity.User;
import com.madongfang.service.ProductService;

@RestController
@RequestMapping(value="/api/products")
public class ProductController {

	@GetMapping
	public List<ProductApi> getProducts(@RequestParam int projectId)
	{
		return productService.getProducts(projectId);
	}
	
	@GetMapping(params="page")
	public Page<ProductApi> getProducts(@RequestAttribute User user, 
			@RequestParam(name="projectId", required=false) List<Integer> projectIds, 
			@PageableDefault(size=100) Pageable pageable)
	{
		return productService.getProducts(user, projectIds, pageable);
	}
	
	@GetMapping(value="/{productId}")
	public ProductApi getProduct(@PathVariable int productId)
	{
		return productService.getProduct(productId);
	}
	
	@Autowired
	private ProductService productService;
}
