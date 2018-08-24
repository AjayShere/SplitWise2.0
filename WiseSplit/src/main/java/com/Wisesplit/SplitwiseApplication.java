package com.Wisesplit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.split")
@EntityScan( basePackages = {"com.split.entity"})
public class SplitwiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}
}
