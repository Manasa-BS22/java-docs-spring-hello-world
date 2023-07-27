package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ANNTraderService {
	
	public List<Product> findAll();
	public List<Product> getApparelProduct();
	public List<Product> getSportingProduct();
	public int deleteProduct(int productID);
	public int updateProduct(int productid, String productname, int price, String description);
	public List<Product> searchProduct(String productName);
	public int addProduct(int productid, String name, int price, String description, String type, String image);

}
