package com.vienna.jaray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JarayOrderProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JarayOrderProducerApplication.class, args);
	}

}
