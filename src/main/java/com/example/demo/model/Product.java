package com.example.demo.model;

import java.sql.Date;

public class Product {
	
	int productid;
	int price;
	String image;
	String name;
	String description;
	Date createdDate;
	String type;
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", price=" + price + ", image=" + image + ", name=" + name
				+ ", description=" + description + ", createdDate=" + createdDate + ", type=" + type + "]";
	}
	
	
	
	
	
}
