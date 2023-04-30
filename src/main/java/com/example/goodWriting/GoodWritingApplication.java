package com.example.goodWriting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GoodWritingApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodWritingApplication.class, args);
	}

}
