package com.example.JavaToEnglish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Java2EnglishApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java2EnglishApplication.class, args);
	}

}
