package com.rjaco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;

	@Size(min = 1, message = "minimum number of characters required is one")
	@Column(name = "category_name", nullable = false, length = 70)
	private String categoryName;

	@ManyToOne
	@JoinColumn(name = "categorytype_id", nullable = false)
	private CategoryType categorytype;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public CategoryType getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(CategoryType categorytype) {
		this.categorytype = categorytype;
	}
	
}
