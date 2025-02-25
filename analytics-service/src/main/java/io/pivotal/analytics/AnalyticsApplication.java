package io.pivotal.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * SpringBoot application for the portfolio microservice.
 * 
 * Responsible for managing the portfolio as well as providing the API.
 * 
 * @author David Ferreira Pinto
 *
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableCircuitBreaker
public class AnalyticsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AnalyticsApplication.class, args);
	}
}
