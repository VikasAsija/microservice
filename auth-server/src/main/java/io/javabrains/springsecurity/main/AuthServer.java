package io.javabrains.springsecurity.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@PropertySource({"classpath:application.yml"})
@ComponentScan(basePackages = {"io.javabrains"})
@SpringBootApplication
@EnableWebSecurity
@EnableEurekaClient
public class AuthServer {

	public static void main(String[] args) {
		SpringApplication.run(AuthServer.class, args);
	}

}
