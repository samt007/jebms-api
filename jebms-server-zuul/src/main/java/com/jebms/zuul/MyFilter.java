package com.jebms.zuul;

import com.netflix.zuul.ZuulFilter;

import javax.servlet.http.HttpServletRequest;

public class MyFilter extends ZuulFilter {

	public static final String TOKEN_HEADER = "Authorization";
    //private static Logger log = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        //RequestContext ctx = RequestContext.getCurrentContext();
        //HttpServletRequest request = ctx.getRequest();
        //ctx.addZuulRequestHeader(TOKEN_HEADER, request.getHeader(TOKEN_HEADER));
        //log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        //log.info("ip: "+this.getIpAddr(request));
        //log.info("Authorization: "+request.getHeader(TOKEN_HEADER));
        return null;
    }
    

	public String getIpAddr(HttpServletRequest request) {
		
    	String ip = request.getHeader("X-Forwarded-For");
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ( ip == null || ip.length() == 0 || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        //使用代理，则获取第一个IP地址
        if ( !(ip == null || ip.length() == 0) && ip.length() > 15) {
			if(ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
        
        return ip;
    }
}