package com.hampcode.model.entity;

public class ProductByCategory {

	private String  nameCategory;
	private Long total;
	
	
	public ProductByCategory(String nameCategory, Long total) {
	
		this.nameCategory = nameCategory;
		this.total = total;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
	
	
}
