package io.microservice.utils;


public class RequestHeadersContext {
	
    public static final String AUTHORIZATION_HEADER = "Authorization";
    
	private String authHeader;
	
    public String getAuthHeader() {
		return authHeader;
	}

	public void setAuthHeader(String authHeader) {
		this.authHeader = authHeader;
	}

	public RequestHeadersContext() {
		
	}
    
	public RequestHeadersContext(String authHeader) {
		this.authHeader = authHeader;
	}
	
	

}
