package io.microservice.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import io.microservice.model.UserData;
import io.microservice.utils.RequestHeadersContext;
import io.microservice.utils.RequestHeadersContextHolder;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private RestTemplate restTemplate;
	
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    	RequestHeadersContext context = new RequestHeadersContext(
                httpServletRequest.getHeader(RequestHeadersContext.AUTHORIZATION_HEADER)
        );
        RequestHeadersContextHolder.setContext(context);
    	String url = "http://auth-server/userDetails";
    	UserData userData = restTemplate.getForObject(url, UserData.class);
    	if(userData != null) {
    		List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(userData.getUserPermissions().get(0)));
			Authentication token = new UsernamePasswordAuthenticationToken(userData, "", authorities);
    		SecurityContextHolder.getContext().setAuthentication(token);
    	} else {
    		httpServletResponse.setContentType("application/json");
        	httpServletResponse.setCharacterEncoding("UTF-8");
        	httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        	return;
    	}
    	filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
