package io.microservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.microservice.model.CatalogItem;
import io.microservice.model.UserRating;
import io.microservice.services.MovieInfo;
import io.microservice.services.UserRatingInfo;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	@RequestMapping("/{userId}")
	@PreAuthorize("hasAuthority('AdminRead')")
	public List<CatalogItem> getCatalog(@PathVariable String userId, HttpServletRequest request) {
		
		UserRating ratings = userRatingInfo.getUserRatings(userId);
		return ratings.getUserRating().stream().map(rating -> {
			return movieInfo.getCatalogItem(rating);
		}).collect(Collectors.toList());
	}
}
