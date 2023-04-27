package com.news.worldnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.news.worldnews.controller"})
@SpringBootApplication
public class WorldNewsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WorldNewsApplication.class, args);
	}

}
