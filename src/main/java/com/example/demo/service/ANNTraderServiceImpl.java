package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

@Service
public class ANNTraderServiceImpl implements ANNTraderService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Product> findAll(){
		
		String sql = "select * from product";
		System.out.println("SQL ::"+sql);
		List<Product> products = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
		Product product = products.get(0);
		System.out.println("product "+product.toString());
		return products;
		
	}
	
	public List<Product> getApparelProduct(){
		
		String sql = "select * from product where type='apparel'";
		System.out.println("SQL ::"+sql);
		List<Product> products = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
		Product product = products.get(0);
		System.out.println("product "+product.toString());
		return products;
		
	}
	
	public List<Product> getSportingProduct(){
		
		String sql = "select * from product where type='sporting'";
		System.out.println("SQL ::"+sql);
		List<Product> products = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
		Product product = products.get(0);
		System.out.println("product "+product.toString());
		return products;
		
	}
	
	public int deleteProduct(int productID) {
		
		String sql = "delete from product where productid="+productID;
		System.out.println("SQL ::"+sql);
		int rowsDeleted = jdbcTemplate.update(sql);
		System.out.println("product deleted "+rowsDeleted);
		return rowsDeleted;
		
		
	}
	
	public int updateProduct(int productID, String productName, int price, String description) {
		
		String sql = "update product set name='"+productName+"', price="+price+",description='"+description+"' where productid="+productID;
		System.out.println("SQL ::"+sql);
		int rowsUpdated = jdbcTemplate.update(sql);
		System.out.println("product updated "+rowsUpdated);
		return rowsUpdated;
		
	}
	
	public int addProduct(int productID, String productName, int price, String description, String type) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date date = new java.util.Date(); 
		String dateformat = formatter.format(date);
	    Date sysdate = Date.valueOf(dateformat);
		
		String sql = "insert into Product values("+productID+","+price+","+"'image','"+productName+"','"+description+"','"+sysdate+"','"+type+"')";
		System.out.println("SQL ::"+sql);
		int rowsUpdated = jdbcTemplate.update(sql);
		System.out.println("product updated "+rowsUpdated);
		return rowsUpdated;
		
		
	}
	
	
	public List<Product> searchProduct(String productName){
		
		String sql = "select * from product where name like '%"+productName+"%'";
		System.out.println("SQL ::"+sql);
		List<Product> products = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
		Product product = products.get(0);
		System.out.println("product "+product.toString());
		return products;
		
	}
	

}
