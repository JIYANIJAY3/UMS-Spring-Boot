package com.inexture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class UmsSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmsSpringbootApplication.class, args);
	}
}
