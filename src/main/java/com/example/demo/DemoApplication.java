package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ANNTraderService;
import com.example.demo.model.Product;

@SpringBootApplication
@RestController
@RequestMapping("/ANNTrader/v1")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired  
	private ANNTraderService annTraderService; 

	@GetMapping("/test")
	String sayHello() {
		return "Hello World from search!";
	}

	@GetMapping("/product")
	public List<Product> getProduct()   
	{  
	//finds all the products
	List<Product> products = annTraderService.findAll();  
	//returns the product list  
	return products;  
	}  

	@GetMapping(value = "/apparelproduct")
	public List<Product> getApparelProduct()   
	{  
	//finds all the products  
	List<Product> products = annTraderService.getApparelProduct();  
	//returns the product list  
	return products;  
	}  
	
	@GetMapping(value = "/sportingproduct")
	public List<Product> getSportingProduct()   
	{  
	//finds all the products  
	List<Product> products = annTraderService.getSportingProduct();  
	//returns the product list  
	return products;  
	}  
	
	@GetMapping(value = "/searchproduct")
	public List<Product> searchProduct(@RequestParam(required = true, name = "productname") String productname)   
	{  
	//finds all the products  
	List<Product> products = annTraderService.searchProduct(productname);  
	//returns the product list  
	return products;  
	}  
	
	@DeleteMapping(value = "/deleteproduct")
	public int getdeleteProduct(@RequestParam(required = true, name = "productid") int productID)   
	{  
	int products = annTraderService.deleteProduct(productID);  
	return products;  
	}  
	
	@PutMapping(value = "/updateProduct")
	public int getupdateProduct(@RequestBody Product updateProductRequest)   
	{  
	int products = annTraderService.updateProduct(updateProductRequest.getProductid(), updateProductRequest.getName(), 
			updateProductRequest.getPrice(), updateProductRequest.getDescription());  
	return products;  
	}  
	
	@PutMapping(value = "/addProduct")
	public int addProduct(@RequestBody Product addProductRequest)   
	{  
	int products = annTraderService.addProduct(addProductRequest.getProductid(), addProductRequest.getName(), 
			addProductRequest.getPrice(), addProductRequest.getDescription(), addProductRequest.getType());  
	return products;  
	} 



}