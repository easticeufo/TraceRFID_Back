package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.ProductApi;
import com.madongfang.service.ProductService;

@RestController
@RequestMapping(value="/api/products")
public class ProductController {

	@GetMapping
	public List<ProductApi> getProducts(@RequestParam int projectId)
	{
		return productService.getProducts(projectId);
	}
	
	@Autowired
	private ProductService productService;
}
