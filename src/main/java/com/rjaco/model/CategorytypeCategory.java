package com.rjaco.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CategorytypeCategoryPK.class)
public class CategorytypeCategory {
	
	@Id
	private CategoryType categoryType;
	
	@Id
	private Category category;

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

}
