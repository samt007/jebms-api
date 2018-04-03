package com.jebms.comm.security.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.jebms.comm.utils.RequestUtil;
import com.jebms.comm.utils.IPUtils;
import com.jebms.comm.utils.TypeConverter;

@Component
public class JwtFeignInterceptor implements RequestInterceptor {
	private final String key = "Authorization";
	
    @Override
    public void apply(RequestTemplate template) {

        if (!template.headers().containsKey(key)) {
            String currentToken = UserUtil.getCurrentToken();
            System.out.println(JSON.toJSONString(UserUtil.getCurrentUser()));
            System.out.println(JSON.toJSONString(UserUtil.getCurrentToken()));
            if (!TypeConverter.isEmpty(currentToken)){
                template.header(key, currentToken);
                template.header("X-Forwarded-For", IPUtils.getIpAddr(RequestUtil.getHttpServletRequest()));
                System.out.println("add the token for feign:"+currentToken+",IP:"+IPUtils.getIpAddr(RequestUtil.getHttpServletRequest()));
            }
        }
    }
}
