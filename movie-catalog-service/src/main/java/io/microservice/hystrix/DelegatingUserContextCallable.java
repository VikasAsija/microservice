package io.microservice.hystrix;

import java.util.concurrent.Callable;

import io.microservice.utils.RequestHeadersContext;
import io.microservice.utils.RequestHeadersContextHolder;

public class DelegatingUserContextCallable<V> implements Callable<V> {
	
	private final Callable<V> delegate;
	private RequestHeadersContext originalUserContext;
	
	public DelegatingUserContextCallable(Callable<V> delegate, RequestHeadersContext userContext) {
		
			 this.delegate = delegate; 
			 this.originalUserContext = userContext; 
	}
	
	public V call() throws Exception { 
		RequestHeadersContextHolder.setContext( originalUserContext ); 
		 try {
			 return delegate.call(); 
		 } finally {
			 this.originalUserContext = null;
		 }
	}
	
	public static <V> Callable<V> create(Callable<V> delegate, RequestHeadersContext userContext) {
	 
		return new DelegatingUserContextCallable<V>(delegate, userContext);
	 }

}
