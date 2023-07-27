package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ANNTraderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.example.demo.model.Product;

@SpringBootApplication
@RestController
@RequestMapping("/ANNTrader/v1")
@Api(value = "ANNTrader Services")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired  
	private ANNTraderService annTraderService; 

	@GetMapping("/test")
	String sayHello() {
		return "Hello World from search! swagger";
	}

	@GetMapping(value = "/product")
	@ApiOperation(value = "product")
	public List<Product> getProduct()   
	{  
	//finds all the products  
	List<Product> products = annTraderService.findAll();  
	//returns the product list  
	return products;  
	}  
	
	@GetMapping(value = "/apparelproduct")
	@ApiOperation(value = "apparelproduct")
	public List<Product> getApparelProduct()   
	{  
	//finds all the products  
	List<Product> products = annTraderService.getApparelProduct();  
	//returns the product list  
	return products;  
	}  
	
	@GetMapping(value = "/sportingproduct")
	@ApiOperation(value = "sportingproduct")
	public List<Product> getSportingProduct()   
	{  
	//finds all the products  
	List<Product> products = annTraderService.getSportingProduct();  
	//returns the product list  
	return products;  
	}  
	
	@GetMapping(value = "/searchproduct")
	@ApiOperation(value = "searchproduct")
	public List<Product> searchProduct(@RequestParam(required = true, name = "productname") String productname)   
	{  
	//finds all the products  
	List<Product> products = annTraderService.searchProduct(productname);  
	//returns the product list  
	return products;  
	}  
	
	@DeleteMapping(value = "/deleteproduct")
	@ApiOperation(value = "deleteproduct")
	public int getdeleteProduct(@RequestParam(required = true, name = "productid") int productID)   
	{  
	int products = annTraderService.deleteProduct(productID);  
	return products;  
	}  
	
	@PutMapping(value = "/updateProduct")
	@ApiOperation(value = "updateProduct")
	public int getupdateProduct(@RequestBody Product updateProductRequest)   
	{  
	int products = annTraderService.updateProduct(updateProductRequest.getProductid(), updateProductRequest.getName(), 
			updateProductRequest.getPrice(), updateProductRequest.getDescription());  
	return products;  
	}  
	
	@PutMapping(value = "/addProduct")
	@ApiOperation(value = "addProduct")
	public int addProduct(@RequestBody Product addProductRequest)   
	{  
	int products = annTraderService.addProduct(addProductRequest.getProductid(), addProductRequest.getName(), 
			addProductRequest.getPrice(), addProductRequest.getDescription(), addProductRequest.getType());
		ServiceBusSenderClient sender = new ServiceBusClientBuilder()
		    .connectionString("https://ANNTraderService.servicebus.windows.net")
		    .sender()
		    .queueName("anntraderqueue")
		    .buildClient();
		List<ServiceBusMessage> messages = Arrays.asList(
		    new ServiceBusMessage("Product is added").setMessageId("1"));

		sender.sendMessages(messages);

		// When you are done using the sender, dispose of it.
		sender.close();
	return products;  
	}

}