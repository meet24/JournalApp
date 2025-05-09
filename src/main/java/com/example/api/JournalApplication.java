package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

	public static void main(String[] args) {
		// SpringApplication.run(JournalApplication.class, args);
		// System.out.println("Hello Meet!!");
		
		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
		if (context instanceof WebServerApplicationContext) {
			int port = ((WebServerApplicationContext) context).getWebServer().getPort();
			System.out.println("====================================> Server running on port: " + port);
		}
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
