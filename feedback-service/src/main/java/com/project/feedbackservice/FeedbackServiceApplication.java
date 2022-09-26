package com.project.feedbackservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@OpenAPIDefinition(info = @Info(title = "Feedback API", version = "2.0", description = "Feedback Microservice"))
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class FeedbackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
