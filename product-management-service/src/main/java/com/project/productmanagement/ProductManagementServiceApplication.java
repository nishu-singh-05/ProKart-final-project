package com.project.productmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@OpenAPIDefinition(info = @Info(title = "Product-Management-API", version = "2.0", description = "Product Management Microservice"))
@EnableEurekaClient
@SpringBootApplication
public class ProductManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementServiceApplication.class, args);
	}

}
