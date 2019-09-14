package com.sadok.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sadok.crud.*"})
public class CrudSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSampleApplication.class, args);
	}

}
