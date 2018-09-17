package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.ManufacturerApi;
import com.madongfang.api.ProductApi;
import com.madongfang.api.ProjectApi;
import com.madongfang.entity.Product;
import com.madongfang.repository.ProductRepository;

@Service
public class ProductService {

	public static ProductApi product2Api(Product product) {
		ProductApi productApi = new ProductApi();
		productApi.setBatchNumber(product.getBatchNumber());
		productApi.setBuildTime(product.getBuildTime());
		productApi.setId(product.getId());
		productApi.setInstallTime(product.getInstallTime());
		productApi.setLatitude(product.getLatitude());
		productApi.setLongitude(product.getLongitude());
		productApi.setModel(product.getModel());
		productApi.setRfid(product.getRfid());
		
		if (product.getManufacturerId() != null)
		{
			ManufacturerApi manufacturer = new ManufacturerApi();
			manufacturer.setId(product.getManufacturerId());
			productApi.setManufacturer(manufacturer);
		}
		if (product.getProjectId() != null)
		{
			ProjectApi project = new ProjectApi();
			project.setId(product.getProjectId());
			productApi.setProject(project);
		}
		
		return productApi;
	}
	
	public List<ProductApi> getProducts(int projectId) {
		
		List<ProductApi> products = new LinkedList<>();
		for (Product product : productRepository.findByProjectId(projectId)) {
			products.add(product2Api(product));
		}
		
		return products;
	}
	
	@Autowired
	private ProductRepository productRepository;
}
