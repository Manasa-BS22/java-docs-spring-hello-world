package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

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
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode;
import com.example.demo.model.Product;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*; 

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
		String image = "image";
		if(addProductRequest.getType().equalsIgnoreCase("Sporting")) {
			image = "https://anntraderstorage.blob.core.windows.net/anntradercontainer/sporting image.jpg";
		}else {
			image = "https://anntraderstorage.blob.core.windows.net/anntradercontainer/garment-industry.jpg";
		}
	int products = annTraderService.addProduct(addProductRequest.getProductid(), addProductRequest.getName(), 
			addProductRequest.getPrice(), addProductRequest.getDescription(), addProductRequest.getType(), image);
		ServiceBusSenderClient sender = new ServiceBusClientBuilder()
		    .connectionString("Endpoint=sb://anntraderservice.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=B3opyhr/nuN0vfc18RKsJvjBP3hF39pDe+ASbFso4XA=")
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
	
	public void sendMail(){  
	      String to = "manasaabs@gmail.com";//change accordingly  
	      String from = "mansomas@in.ibm.com";  
	      String host = "anntraderapi.azurewebsites.net";//or IP address  
	  
	     //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);  
	  
	     //compose the message  
	      try{  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(from));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	         message.setSubject("AZURE SERVICE NOTIFICATION");  
	         message.setText("Product has been added");  
	  
	         // Send message  
	         Transport.send(message);  
	         System.out.println("message sent successfully....");  
	  
	      }catch (MessagingException mex) {mex.printStackTrace();}  
	   }  
	
	
}