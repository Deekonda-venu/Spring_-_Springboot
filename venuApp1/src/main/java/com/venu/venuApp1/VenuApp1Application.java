package com.venu.venuApp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.venu.venuApp1", "com.Exple.learn"})
public class VenuApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(VenuApp1Application.class, args);
	}

}
