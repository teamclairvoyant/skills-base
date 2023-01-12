package com.clairvoyant.clarise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.clairvoyant.clarise.*")
public class ClariseMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClariseMainApplication.class, args);
	}

}
