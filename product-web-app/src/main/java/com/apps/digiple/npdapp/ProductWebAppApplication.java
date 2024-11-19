package com.apps.digiple.npdapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.apps.digiple.npdapp.db") 
@EntityScan("com.apps.digiple.npdapp.bean")
@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class ProductWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductWebAppApplication.class, args);
	}

}
