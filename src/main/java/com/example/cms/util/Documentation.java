package com.example.cms.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class Documentation {

	Contact contact() {
		return new Contact().name("pavan").url("https://leetcode.com/").email("budurupavankumar268@gmail.com");
	}

	@Bean
	Info info() {
		return new Info().title("Content Management System").description("RESTFul Api with Creating Website  and perform CRUD Operation.")
				.version("vi").contact(contact());
	}

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
}
