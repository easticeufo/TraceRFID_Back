package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.ManufacturerApi;
import com.madongfang.api.ReturnApi;
import com.madongfang.entity.Manufacturer;
import com.madongfang.entity.Product;
import com.madongfang.exception.HttpNotFoundException;
import com.madongfang.repository.ManufacturerRepository;
import com.madongfang.repository.ProductRepository;

@Service
public class ManufacturerService {

	public static ManufacturerApi manufacturer2Api(Manufacturer manufacturer) {
		return manufacturer2Api(manufacturer, null);
	}
	
	public static ManufacturerApi manufacturer2Api(Manufacturer manufacturer, ManufacturerApi manufacturerApi) {
		if (manufacturerApi == null)
		{
			manufacturerApi = new ManufacturerApi();
		}
		manufacturerApi.setId(manufacturer.getId());
		manufacturerApi.setName(manufacturer.getName());
		return manufacturerApi;
	}
	
	public List<ManufacturerApi> getManufacturers() {
		List<ManufacturerApi> manufacturers = new LinkedList<>();
		for (Manufacturer manufacturer : manufacturerRepository.findAll()) {
			manufacturers.add(manufacturer2Api(manufacturer));
		}
		
		return manufacturers;
	}
	
	public ManufacturerApi getManufacturer(int manufacturerId) {
		Manufacturer manufacturer = manufacturerRepository.findOne(manufacturerId);
		if (manufacturer == null)
		{
			throw new HttpNotFoundException(new ReturnApi(-1, "manufacturer not found"));
		}
		
		return manufacturer2Api(manufacturer);
	}
	
	public ManufacturerApi setManufacturer(int manufacturerId, ManufacturerApi manufacturerApi)
	{
		Manufacturer manufacturer = manufacturerRepository.findOne(manufacturerId);
		if (manufacturer == null)
		{
			throw new HttpNotFoundException(new ReturnApi(-1, "manufacturer not found"));
		}
		
		if (manufacturerApi.getName() != null)
		{
			manufacturer.setName(manufacturerApi.getName());
		}
		manufacturerRepository.save(manufacturer);
		
		return manufacturer2Api(manufacturer, manufacturerApi);
	}
	
	public ManufacturerApi addManufacturer(ManufacturerApi manufacturerApi)
	{
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(manufacturerApi.getName());
		manufacturerRepository.save(manufacturer);
		
		return manufacturer2Api(manufacturer, manufacturerApi);
	}
	
	public void deleteManufacturer(int manufacturerId)
	{
		manufacturerRepository.delete(manufacturerId);
		List<Product> products = productRepository.findByManufacturerId(manufacturerId);
		for (Product product : products) {
			product.setManufacturerId(null);
		}
		productRepository.save(products);
		
		return;
	}
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private ProductRepository productRepository;
}
