package io.microservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.microservice.model.CatalogItem;
import io.microservice.model.Movie;
import io.microservice.model.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallBackCatalogItem",
	commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200000"),
		@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
		@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
		@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")
	},
	threadPoolKey="",
	threadPoolProperties= {
		@HystrixProperty(name = "coreSize", value = "20"),
		@HystrixProperty(name = "maxQueueSize", value = "10")
	})
	public CatalogItem getCatalogItem(Rating rating) {
		String url = "http://movie-info-service/movies/" + rating.getMovieId();
		Movie movie = restTemplate.getForObject(url, Movie.class);
		return new CatalogItem(movie.getName(), "Test", rating.getRating());
	}
	
	private CatalogItem getFallBackCatalogItem(Rating rating) {
		
		return new CatalogItem("No movie", "No desc", rating.getRating());
	}
}
