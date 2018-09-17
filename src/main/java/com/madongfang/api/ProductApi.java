package com.madongfang.api;

import java.util.Date;

public class ProductApi {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}

	public Date getInstallTime() {
		return installTime;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public ManufacturerApi getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerApi manufacturer) {
		this.manufacturer = manufacturer;
	}

	public ProjectApi getProject() {
		return project;
	}

	public void setProject(ProjectApi project) {
		this.project = project;
	}

	private Integer id;
	
	private String rfid;
	
	private String model; // 型号
	
	private String batchNumber; // 批次号
	
	private Date buildTime; // 生产时间
	
	private Date installTime; // 安装时间
	
	private Double latitude; // 纬度
	
	private Double longitude; // 经度
	
	private ManufacturerApi manufacturer;
	
	private ProjectApi project;
}
