package com.awokou.springbootbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringbootBackendApplication {
	public static void main(String[] args) {

		SpringApplication.run(SpringbootBackendApplication.class, args);

		log.info("********************Deployment********************************");
	}
}
