package io.microservice.hystrix;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import io.microservice.utils.RequestHeadersContextHolder;

public class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {
	
	private HystrixConcurrencyStrategy existingConcurrencyStrategy; 
	
	public ThreadLocalAwareStrategy(HystrixConcurrencyStrategy existingConcurrencyStrategy) { 
		this.existingConcurrencyStrategy = existingConcurrencyStrategy;
	 }
	
	 @Override
	 public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize){ 
		 return existingConcurrencyStrategy != null ? existingConcurrencyStrategy.getBlockingQueue(maxQueueSize)
				 									: super.getBlockingQueue(maxQueueSize);
	 }
	 
	 @Override
	 public <T> Callable<T> wrapCallable(Callable<T> callable) {
		 return existingConcurrencyStrategy != null ? existingConcurrencyStrategy.wrapCallable(new DelegatingUserContextCallable<T>(callable, RequestHeadersContextHolder.getContext()))
				 									: super.wrapCallable(new DelegatingUserContextCallable<T>(callable, RequestHeadersContextHolder.getContext()));
	 }

}
