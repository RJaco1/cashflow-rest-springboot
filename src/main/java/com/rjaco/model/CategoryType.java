package com.rjaco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Category information")
@Entity
@Table(name = "category_type")
public class CategoryType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryTypeId;
	
	@ApiModelProperty(notes = "categoryType must be at least one character long")
	@Size(min = 1, message = "minimum number of characters required is one")
	@Column(name = "category_type", nullable = false, length = 70)
	private String categoryType;
	
	public int getCategoryTypeId() {
		return categoryTypeId;
	}
	
	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}
	
	public String getCategoryType() {
		return categoryType;
	}
	
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryTypeId;
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
		CategoryType other = (CategoryType) obj;
		if (categoryTypeId != other.categoryTypeId)
			return false;
		return true;
	}
	
}
