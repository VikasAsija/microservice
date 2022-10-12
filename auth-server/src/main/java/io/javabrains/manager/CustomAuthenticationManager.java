package io.javabrains.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import io.javabrains.model.SessionDetails;
import io.javabrains.model.UserData;

public class CustomAuthenticationManager implements AuthenticationManager {
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		UserData userData = validateUserCredentials((String)auth.getPrincipal(), (String)auth.getCredentials());
		return new UsernamePasswordAuthenticationToken(userData, null);
		
	}
	
	private UserData validateUserCredentials(String userName, String password) {
		UserData userData = new UserData();
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionId("session123");
		sessionDetails.setUserId("user123");
		
		List<String> userPermissions = new ArrayList<>();
		userPermissions.add("AdminRead");
		userData.setSessionDetails(sessionDetails);
		userData.setUserPermissions(userPermissions);
		return userData;
	}

}
