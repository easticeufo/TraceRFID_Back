package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.madongfang.api.ManufacturerApi;
import com.madongfang.api.ProductApi;
import com.madongfang.api.ProjectApi;
import com.madongfang.api.ReturnApi;
import com.madongfang.entity.Manufacturer;
import com.madongfang.entity.Product;
import com.madongfang.entity.Project;
import com.madongfang.entity.User;
import com.madongfang.exception.HttpNotFoundException;
import com.madongfang.repository.ManufacturerRepository;
import com.madongfang.repository.ProductRepository;
import com.madongfang.repository.ProjectRepository;
import com.madongfang.repository.UserProjectRepository;

@Service
public class ProductService {

	public static ProductApi product2Api(Product product) {
		return product2Api(product, null);
	}
	
	public static ProductApi product2Api(Product product, ProductApi productApi) {
		if (productApi == null)
		{
			productApi = new ProductApi();
		}
		
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
	
	public Page<ProductApi> getProducts(User user, List<Integer> projectIds, Pageable pageable) {
		
		Page<Product> products;
		
		if (projectIds == null || projectIds.size() == 0)
		{
			if (user.getLevel() <= 1)
			{
				products = productRepository.findAll(pageable);
			}
			else
			{
				projectIds = userProjectRepository.findProjectIdsByUserId(user.getId());
				products = productRepository.findByProjectIdIn(projectIds, pageable);
			}
		}
		else
		{
			products = productRepository.findByProjectIdIn(projectIds, pageable);
		}
		logger.info("projectIds={}", projectIds);
		
		return products.map(new Converter<Product, ProductApi>() {

			@Override
			public ProductApi convert(Product product) {
				ProductApi productApi = product2Api(product);
				if (product.getProjectId() != null)
				{
					Project project = projectRepository.findOne(product.getProjectId());
					productApi.setProject(ProjectService.project2Api(project, productApi.getProject()));
				}
				if (product.getManufacturerId() != null)
				{
					Manufacturer manufacturer = manufacturerRepository.findOne(product.getManufacturerId());
					productApi.setManufacturer(ManufacturerService.manufacturer2Api(manufacturer, productApi.getManufacturer()));
				}
				return productApi;
			}
		});
	}
	
	public ProductApi getProduct(int productId) {
		Product product = productRepository.findOne(productId);
		if (product == null)
		{
			throw new HttpNotFoundException(new ReturnApi(-1, "product not found"));
		}
		ProductApi productApi = product2Api(product);
		
		if (product.getProjectId() != null)
		{
			Project project = projectRepository.findOne(product.getProjectId());
			productApi.setProject(ProjectService.project2Api(project, productApi.getProject()));
		}
		if (product.getManufacturerId() != null)
		{
			Manufacturer manufacturer = manufacturerRepository.findOne(product.getManufacturerId());
			productApi.setManufacturer(ManufacturerService.manufacturer2Api(manufacturer, productApi.getManufacturer()));
		}
		
		return productApi;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private UserProjectRepository userProjectRepository;
}
