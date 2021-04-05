package com.prd.skillbase;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class SkillBaseMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillBaseMainApplication.class, args);
	}

}
