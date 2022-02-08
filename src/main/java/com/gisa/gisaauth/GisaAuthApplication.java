package com.gisa.gisaauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gisa")
public class GisaAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(GisaAuthApplication.class, args);
	}

}
