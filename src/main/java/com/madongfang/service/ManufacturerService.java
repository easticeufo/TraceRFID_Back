package com.madongfang.service;

import org.springframework.stereotype.Service;

import com.madongfang.api.ManufacturerApi;
import com.madongfang.entity.Manufacturer;

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
}
