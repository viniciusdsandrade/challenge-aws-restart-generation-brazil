package com.desafio.restartawsgeneration.RestartAwsGenerationApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"com.desafio.restartawsgeneration"})
public class RestartAwsGenerationApplication {

	public static void main(String[] args) {
		run(RestartAwsGenerationApplication.class, args);
	}
}
