package io.microservice.moviecatalogservice;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import io.microservice.utils.RestTemplateInterceptor;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"io.microservice"})
@PropertySource({"classpath:application.yml"})
@EnableCircuitBreaker
//@EnableResourceServer 
public class MovieCatalogServiceApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		 List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		 if (interceptors==null){ 
			 template.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
		 }
		 else{
			 interceptors.add(new RestTemplateInterceptor());
			 template.setInterceptors(interceptors);
		 }
		 return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
