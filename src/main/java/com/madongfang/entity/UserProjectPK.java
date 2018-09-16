package com.madongfang.entity;

import java.io.Serializable;

public class UserProjectPK implements Serializable {

	public UserProjectPK() {
		super();
	}

	public UserProjectPK(int userId, int projectId) {
		super();
		this.userId = userId;
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + projectId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProjectPK other = (UserProjectPK) obj;
		if (projectId != other.projectId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
	
	private int userId;
	
	private int projectId;
}
