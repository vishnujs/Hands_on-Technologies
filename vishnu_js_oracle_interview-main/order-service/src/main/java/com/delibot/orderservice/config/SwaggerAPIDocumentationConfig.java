package com.delibot.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerAPIDocumentationConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("DeliBot REST operations API in Spring-Boot 2")
				.description(
						"Sample REST API for monitoring using Spring Boot, Prometheus and Graphana ")
				.termsOfServiceUrl("").version("0.0.1-SNAPSHOT").contact(new Contact("Vishnu J S", "", "jsvishnujs@gmail.com")).build();
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

}
