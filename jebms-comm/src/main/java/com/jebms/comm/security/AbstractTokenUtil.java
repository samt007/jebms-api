package com.jebms.comm.security;

import com.jebms.comm.redis.RedisRepository;
import com.jebms.comm.utils.IPUtils;
import com.jebms.comm.utils.StringHelper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Token 工具类
 *
 * @author samt007@qq.com
 */
public abstract class AbstractTokenUtil {

    /**
     * Logger
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTokenUtil.class);
    //protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     * Token 类型
     */
    public static final String TOKEN_TYPE_BEARER = "Bearer";
    /**
     * 权限缓存前缀
     */
    private static final String REDIS_PREFIX_AUTH = "auth:";
    /**
     * 用户信息缓存前缀
     */
    private static final String REDIS_PREFIX_USER = "user-details:";

    /**
     * redis repository
     */
    @Autowired
    private RedisRepository redisRepository;
    /**
     * secret
     */
    private String secret;
    /**
     * token的绝对过期时间(一般为7天)
     */
    private Long tokenExpiration;
    /**
     * 过期时间(指token留在缓存[redis]的过期时间，每次使用会自动更新)
     */
    private Long expiration;

    /**
     * 获取用户名
     *
     * @param token Token
     * @return String
     */
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * 获取token过期时间
     *
     * @param token Token
     * @return Date
     */
    public Date getExpiredFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getExpiration() : null;
    }

    /**
     * 获得 Claims
     *
     * @param token Token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            LOGGER.warn("getClaimsFromToken exception", e);
            claims = null;
        }
        return claims;
    }

    /**
     * 计算token过期时间
     *
     * @return Date
     */
    private Date generateTokenExpired() {
        return new Date(System.currentTimeMillis() + tokenExpiration * 1000);
    }

    /**
     * 计算缓存token的过期时间
     *
     * @return Date
     */
    /*private Date generateExpired() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }*/

    /**
     * 判断 Token 是否过期
     *
     * @param token Token
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        Date expirationDate = getExpiredFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 生成 Token
     *
     * @param userDetails 用户信息
     * @return String
     */
    public String generateToken(HttpServletRequest request,UserDetails userDetails) {
        String token = Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setExpiration(generateTokenExpired())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();

        String key = REDIS_PREFIX_AUTH + IPUtils.getIpAddr(request)+":"+userDetails.getUsername();
        redisRepository.setExpire(key, token, expiration);
        putUserDetails(request,userDetails);
        return token;
    }

    /**
     * 获取当前用户的 Token
     *
     * @param userDetails 用户信息
     * @return String
     */
    public String getToken(HttpServletRequest request,UserDetails userDetails) {
        String key = REDIS_PREFIX_AUTH + IPUtils.getIpAddr(request)+":"+userDetails.getUsername();
        return redisRepository.get(key);
    }

    /**
     * 自动刷新 Token 的缓存过期时间
     *
     * @param userDetails 用户信息
     * @return String
     */
    public void updateToken(HttpServletRequest request,UserDetails userDetails) {
    	//为减少过于频繁的redis的刷新时间，这里添加一个逻辑：在１/10的有效时间之内，重复使用不需要更新缓存的过期时间
    	String postKey=IPUtils.getIpAddr(request)+":"+userDetails.getUsername();
    	LOGGER.info("更新缓存的过期时间基准时间为: <={}", expiration*0.9);
    	if(redisRepository.getExpire(REDIS_PREFIX_AUTH+postKey)<=(expiration*0.9)){
            String[] keys={REDIS_PREFIX_AUTH+postKey,REDIS_PREFIX_USER +postKey};
            redisRepository.updateExpire(keys, expiration);
            LOGGER.info("更新缓存的过期时间updateToken为: {}", expiration);
    	}
    }

    /**
     * 验证 Token
     *
     * @param token Token
     * @return Boolean
     */
    public Boolean validateToken(HttpServletRequest request,String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + IPUtils.getIpAddr(request)+":"+username;
        String redisToken = redisRepository.get(key);
        return StringHelper.isNotEmpty(token) && !isTokenExpired(token) && token.equals(redisToken);
    }

    /**
     * 移除 Token
     *
     * @param token Token
     */
    public void removeToken(HttpServletRequest request,String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + IPUtils.getIpAddr(request)+":"+username;
        redisRepository.del(key);
        delUserDetails(request,username);
    }

    /**
     * 获得用户信息 Json 字符串
     *
     * @param token Token
     * @return String
     */
    protected String getUserDetailsString(HttpServletRequest request,String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_USER + IPUtils.getIpAddr(request)+":"+username;
        return redisRepository.get(key);
    }

    /**
     * 获得用户信息
     *
     * @param token Token
     * @return UserDetails
     */
    public abstract UserDetails getUserDetails(String token);

    /**
     * 存储用户信息
     *
     * @param userDetails 用户信息
     */
    private void putUserDetails(HttpServletRequest request,UserDetails userDetails) {
        String key = REDIS_PREFIX_USER + IPUtils.getIpAddr(request)+":"+userDetails.getUsername();
        redisRepository.setExpire(key, new Gson().toJson(userDetails), expiration);
    }

    /**
     * 删除用户信息
     *
     * @param username 用户名
     */
    private void delUserDetails(HttpServletRequest request,String username) {
        String key = REDIS_PREFIX_USER + IPUtils.getIpAddr(request)+":"+ username;
        redisRepository.del(key);
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
    	//LOGGER.info("setExpiration:"+expiration);
        this.expiration = expiration;
    }

	public Long getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(Long tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}
}