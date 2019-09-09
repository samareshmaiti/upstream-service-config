package com.stackroute.demo;

import com.stackroute.demo.service.DataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//DataService dataService=new DataService();
//dataService.send();
		ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
		DataService dataService = context.getBean("dataService", DataService.class);
		dataService.send();
	}

}
