package com.azmath.hms;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class HmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsApplication.class, args);
	}

}
