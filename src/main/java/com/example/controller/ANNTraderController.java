package src.main.java.com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ANNTraderController {

	@RequestMapping("/")
	String sayHello() {
		System.out.println("Hello, World! from azure into controller");
		return "Hello World! from azure for into controller";
	}
}
