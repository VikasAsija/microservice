package io.microservice.zuulgateway;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import io.microservice.filters.GatewayFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean requestFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(gatewayFilter());
		registration.addUrlPatterns("/*");
		registration.setName("gatewayFilter");
		registration.setOrder(1);
		return registration;
	}
	
	@Bean(name = "gatewayFilter")
	public Filter gatewayFilter() {

		return new GatewayFilter();
	}
}
