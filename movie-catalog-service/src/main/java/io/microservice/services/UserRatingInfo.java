package io.microservice.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.microservice.model.Rating;
import io.microservice.model.UserRating;
import io.microservice.utils.Util;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallBackUserRating",
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
	public UserRating getUserRatings(String userId) {
		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/" + userId, UserRating.class);
		return ratings;
	}

	private UserRating getFallBackUserRating(String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("1", 4), 
				new Rating("2", 3));
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}
}
