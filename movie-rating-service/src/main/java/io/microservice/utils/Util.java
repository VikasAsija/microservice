package io.microservice.utils;

public class Util {
	
	private static final ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<Integer>();
	
	public static void setValue() {
		
		threadLocalCounter.set(0);
	}

	public static Integer getValue() {
		
		return threadLocalCounter.get();
	}
}
