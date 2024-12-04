package com.felix.Book.Management.Service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookManagementServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementServiceApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner keepRunning() {
//		return args -> {
//			System.out.println("Application is running...");
//			Thread.currentThread().join(); // Keeps the main thread alive
//		};
//	}

}
