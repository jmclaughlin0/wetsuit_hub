package com.nyxgroup.wetsuit_hubweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WetsuitHubWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetsuitHubWebApplication.class, args);
	}

	@Bean
	public WetsuitService wetsuitService(){
		return new WetsuitService();
	}
}
