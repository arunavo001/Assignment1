package com.upgrad.mba;

import com.upgrad.mba.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HireWheelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HireWheelsApplication.class, args);
	}

	@Bean
	CommandLineRunner init (InitService initService){
		return args -> {
			initService.init();
		};
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
