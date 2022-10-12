package io.javabrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.javabrains.model.AuthenticationRequest;
import io.javabrains.model.UserData;
import io.javabrains.utils.JWTUtils;

@Service
public class AuthenticationService {
	
	@Autowired
	private JWTUtils utils;
	
	@Autowired
	private AuthenticationManager authManager;
	
	public String login(AuthenticationRequest loginRequest) {
		
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
		return utils.createToken((UserData)authentication.getPrincipal());
	}
}
