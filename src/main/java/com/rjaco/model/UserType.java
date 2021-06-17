package com.rjaco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_type")
public class UserType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userTypeId;
	
	@Column(name = "user_type", nullable = false, length = 20)
	private String userType;
	
	public int getUserTypeId() {
		return userTypeId;
	}
	
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userTypeId;
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
		UserType other = (UserType) obj;
		if (userTypeId != other.userTypeId)
			return false;
		return true;
	}
	
}
