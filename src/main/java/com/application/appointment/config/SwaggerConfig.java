package com.application.appointment.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class provides swagger configuration for the API documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("appointment-application").select()
				.apis(RequestHandlerSelectors.basePackage("com.application.appointment.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("REST API ",
				"Application to provide appointment details for a doctor", "Version 1.0",
				"Terms of service", new Contact("Vishnu", "", "vishnugodara2405@gmail.com"),
				"", "", Collections.emptyList());
	}
}
