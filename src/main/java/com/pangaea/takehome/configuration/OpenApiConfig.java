package com.pangaea.takehome.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig{

	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI()
				.info(new Info()
						.title("back-end Challenge APP")
						.contact(myContactInfo())
						.version("0.1")
						.description("back-end Challenge APP")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

	private Contact myContactInfo() {
		Contact contact = new Contact();
		contact.setName("Mohammed Elsaeed");
		contact.setEmail("eng.mo.abdu@gmail.com");
		contact.setUrl("https://www.linkedin.com/in/engmoelsaeed");
		return contact;
	}
}
