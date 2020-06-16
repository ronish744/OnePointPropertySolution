package com.onepointpropertybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
//Reference: https://dzone.com/articles/spring-cloud-amp-spring-bootimplementing-eureka-se
public class DiscoveryClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientApplication.class, args);
	}
}
