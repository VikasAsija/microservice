package io.microservice.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component 
public class UserContextFilter implements Filter { 
 
	 @Override
	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		 									throws IOException, ServletException {
		 HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		 System.out.println(httpServletRequest.getHeader("correlation"));
		 filterChain.doFilter(httpServletRequest, servletResponse);
		}
}
