package com.project.emailservice;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "Ticket API", version = "2.0", description = "Book Updates"))
@SpringBootApplication
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}

//server:port: 8095 spring:application:name: EMAIL-SERVICE
