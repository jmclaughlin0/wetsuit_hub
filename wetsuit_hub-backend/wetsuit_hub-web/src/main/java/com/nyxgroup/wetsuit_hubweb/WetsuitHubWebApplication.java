package com.nyxgroup.wetsuit_hubweb;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableScheduling
public class WetsuitHubWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetsuitHubWebApplication.class, args);
	}

	public class ServiceConfiguration {
		public ServiceConfiguration(WetsuitsRepository wetsuitsRepository) {
			this.wetsuitsRepository = wetsuitsRepository;
		}
		private final WetsuitsRepository wetsuitsRepository;

		@Bean
		public WetsuitService wetsuitService() {
			return new WetsuitService(wetsuitsRepository);
		}

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurerAdapter() {
				@Override
				public void addCorsMappings(@NotNull CorsRegistry registry) {
					registry.addMapping("/**").allowedOrigins("http://localhost:3000");
					registry.addMapping("/**").allowedOrigins("https://main.dx53rkj17w7f5.amplifyapp.com");
				}
			};
		}
	}
}