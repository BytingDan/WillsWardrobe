package com.willswardrobe.WillsWardrobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.willswardrobe.WillsWardrobe.wardrobe")
@SpringBootApplication
public class WillsWardrobeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WillsWardrobeApplication.class, args);
	}

}
