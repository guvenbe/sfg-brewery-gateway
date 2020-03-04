package com.github.sfgbeerworks.sfgbrewerygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SfgBreweryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfgBreweryGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){

		return builder.routes()
				.route(r -> r.path("/api/v1/beer/*", "beerUpc/*")
						.uri("http://localhost:8080")
						.id("beer-service"))
				.route(r -> r.path("/api/v1/beer/*/inventory" )
						.uri("http://localhost:8082")
						.id("inventory-service"))
				.route(r -> r.path(("/api/v1/customers/**"))
						.uri("http://localhost:8081")
						.id("order-service"))
				.build();

	}
}
