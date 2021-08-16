package com.example.demo.com.example.cowin;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.Object.*;
@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	static
	apiRequest apirequest;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
		apirequest.sendRequest();
	}

}
