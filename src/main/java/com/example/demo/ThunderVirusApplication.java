package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ThunderVirusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThunderVirusApplication.class, args);
	}

}
