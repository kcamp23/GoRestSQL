package com.careerdevs.GoRestSQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@GetMapping()
@RestController



@SpringBootApplication
public class GoRestSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoRestSqlApplication.class, args);
	}

}
