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

	@RequestMapping("/test")
	String sayHello() {
		return "Hello World!";
	}

	@GetMapping("/product")
	public List<Product> getProduct()   
	{  
	//finds all the products  
	List<Product> products = annTraderService.findAll();  
	//returns the product list  
	return products;  
	}  

}