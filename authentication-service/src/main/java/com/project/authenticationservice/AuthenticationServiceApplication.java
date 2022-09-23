package com.project.authenticationservice;

import com.project.authenticationservice.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@OpenAPIDefinition(info = @Info(title = "Authentication API", version = "2.0", description = "Authentication Microservice"))
@EnableEurekaClient
@SpringBootApplication
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
