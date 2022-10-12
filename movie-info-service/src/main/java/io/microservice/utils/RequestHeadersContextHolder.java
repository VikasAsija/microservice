package io.microservice.utils;

public final class RequestHeadersContextHolder {
	
    private static final ThreadLocal<RequestHeadersContext> requestHeaderContext = new ThreadLocal<>();
 
    public static void clearContext() {
        requestHeaderContext.remove();
    }
 
    public static RequestHeadersContext getContext() {
        RequestHeadersContext context = requestHeaderContext.get();
        if (context == null) {
            context = createEmptyContext();
            requestHeaderContext.set(context);
        }
        return context;
    }
 
    public static void setContext(RequestHeadersContext context) {
        requestHeaderContext.set(context);
    }
 
    public static RequestHeadersContext createEmptyContext() {
        return new RequestHeadersContext();
    }
}
