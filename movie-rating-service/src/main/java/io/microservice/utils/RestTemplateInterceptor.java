package io.microservice.utils;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor { 
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
	throws IOException {
		
		RequestHeadersContext headerContext = RequestHeadersContextHolder.getContext();
		HttpHeaders headers = request.getHeaders(); 
		headers.add(RequestHeadersContext.AUTHORIZATION_HEADER, headerContext.getAuthHeader());
		return execution.execute(request, body);
	}
}
