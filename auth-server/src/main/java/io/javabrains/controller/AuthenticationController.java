package io.javabrains.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.model.AuthenticationRequest;
import io.javabrains.model.AuthenticationResponse;
import io.javabrains.model.UserData;
import io.javabrains.service.AuthenticationService;
import io.javabrains.utils.JWTUtils;

@RestController
@RequestMapping
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService service;
	
	@Autowired
	private JWTUtils utils;
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('AdminWrite')")
	public ResponseEntity<?> hello() {
		return ResponseEntity.ok(new String("Hello World"));
	}
	
	@PostMapping(value="/signin")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest loginRequest) {
		String token = service.login(loginRequest);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@GetMapping(value="/userDetails")
	public ResponseEntity<?> userDetails(HttpServletRequest request) {
		UserData authToken = utils.authoriseToken(request);
		return ResponseEntity.ok(authToken);
	}
}
