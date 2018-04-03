package com.jebms.comm.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.jebms.comm.utils.RequestUtil;

/**
 * Created by samt007@qq.com
 */
public final class UserUtil {
	
	private static final String AUTHORIZATION = "authorization";

    public static String getCurrentToken() {
        return RequestUtil.getHeaders(RequestUtil.getHttpServletRequest()).get(AUTHORIZATION);
    }
    
    /**
     * 获取当前登录者对象
     *
     * @param <T> the type parameter
     * @return the current user
     */
    @SuppressWarnings("unchecked")
    public static <T extends UserDetails> T getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (T) ((authentication.getPrincipal() instanceof String)?null:authentication.getPrincipal());
    }
}
