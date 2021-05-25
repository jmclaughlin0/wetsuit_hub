package com.nyxgroup.wetsuit_hubweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class WetsuitHubWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetsuitHubWebApplication.class, args);
	}


	@Configuration
	public class ServiceConfiguration {
		public ServiceConfiguration(MockDatabaseRepository mockDatabaseRepository) {
			this.mockDatabaseRepository = mockDatabaseRepository;
		}
		private final MockDatabaseRepository mockDatabaseRepository;

		@Bean
		public WetsuitService wetsuitService() {
			return new WetsuitService(mockDatabaseRepository);
		}

	}
}
