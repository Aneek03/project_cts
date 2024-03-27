package com.aneek.book;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() { // to convert one object  to another
		
		return new ModelMapper();
	}

}
