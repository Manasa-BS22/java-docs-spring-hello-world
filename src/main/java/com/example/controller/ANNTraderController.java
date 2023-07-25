package src.main.java.com.example.controller;

import src.main.java.com.example.demo.RequestMapping;
import src.main.java.com.example.demo.RestController;

@RestController
public class ANNTraderController {

	@RequestMapping("/")
	String sayHello() {
		System.out.println("Hello, World! from azure into controller");
		return "Hello World! from azure for into controller";
	}
}
