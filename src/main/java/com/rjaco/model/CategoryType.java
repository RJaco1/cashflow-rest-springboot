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
	private int categorytypeId;
	
	@ApiModelProperty(notes = "categorytype must be at least one character long")
	@Size(min = 1, message = "minimum number of characters required is one")
	@Column(name = "category_type", nullable = false, length = 70)
	private String categorytype;

	public int getCategorytypeId() {
		return categorytypeId;
	}

	public void setCategorytypeId(int categorytypeId) {
		this.categorytypeId = categorytypeId;
	}

	public String getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}
	
}
