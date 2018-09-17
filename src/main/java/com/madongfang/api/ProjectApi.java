package com.madongfang.api;

public class ProjectApi {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConstructor() {
		return constructor;
	}

	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	private Integer id;
	
	private String name;
	
	private String constructor; // 施工方(==采购方)
	
	private String supervisor; // 监理单位
}
