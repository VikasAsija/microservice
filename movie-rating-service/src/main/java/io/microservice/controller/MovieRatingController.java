package io.microservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.microservice.model.Rating;
import io.microservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class MovieRatingController {
	
	@RequestMapping("/{userId}")
//	@PreAuthorize("hasAuthority('AdminWrite')")
	public UserRating getUserRating(@PathVariable String userId) {
		
		List<Rating> ratingList = new ArrayList<>();
		Rating rating = new Rating("1", 5);
		Rating rating1 = new Rating("2", 4);
		ratingList.add(rating);
		ratingList.add(rating1);
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratingList);
		return userRating;
	}
}
