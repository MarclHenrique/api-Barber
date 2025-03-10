package com.barber.apiBarber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.barber.apiBarber.config")
@SpringBootApplication
public class ApiBarberApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBarberApplication.class, args);
	}

}
