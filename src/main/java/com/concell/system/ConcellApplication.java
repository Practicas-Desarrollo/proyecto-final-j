package com.concell.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ConcellApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcellApplication.class, args);
	}

}
