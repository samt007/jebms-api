package com.jebms.comm.security.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.stereotype.Component;

import com.jebms.comm.utils.TypeConverter;

@Component
public class JwtFeignInterceptor implements RequestInterceptor {
	private final String key = "Authorization";
	
    @Override
    public void apply(RequestTemplate template) {

        if (!template.headers().containsKey(key)) {
            String currentToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJXWDIxNDQ5MiIsImV4cCI6MTUyMzI2NTI3MX0.5_HLzm79ylKW6i8mV53LUDyb7g-4bqgL8ZQ-tkHP_WKISYe3GNuwd7Fo_u28b5CQBMrF0oXKxWPg9x7yGJRIoA";// UserUtils.getCurrentToken();
            if (!TypeConverter.isEmpty(currentToken)){
                template.header(key, currentToken);
                System.out.println("add the token for feign!");
            }
        }
    }
}
