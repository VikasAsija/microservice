package io.javabrains.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javabrains.model.UserData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	public static final String secret = "89NLA80gpinGiaIWaTzEZzCM1VaCGStTiXFM3AZACbnof0DlXBjtMb1BotvaV76";
	
	public String createToken(UserData userData) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("userPermissions", userData.getUserPermissions());
		claims.put("sessionDetails", userData.getSessionDetails());
		
		return Jwts.builder().setClaims(claims).setSubject(userData.getSessionDetails().getUserName()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public String validateToken(String token) {
		
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String header = new String(decoder.decode(chunks[0]));
		System.out.println("Header :"+header);
		return new String(decoder.decode(chunks[1]));
	}
	
	public UserData authoriseToken(HttpServletRequest request) {
		String jwt = null;
		ObjectMapper mapper = new ObjectMapper();
		final String correlationHeader = request.getHeader("correlation");
		final String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			jwt = authHeader.substring(7);
			String claims = validateToken(jwt);
			System.out.println(claims);
			UserData userData;
			try {
				userData = mapper.readValue(claims, UserData.class);
				return userData;
				/*List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(userData.getUserPermissions().get(0)));*/
//				return new UsernamePasswordAuthenticationToken(userData, "", authorities);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
