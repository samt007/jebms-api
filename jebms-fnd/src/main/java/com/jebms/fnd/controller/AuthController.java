package com.jebms.fnd.controller;

import com.google.gson.Gson;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.security.AuthenticationTokenFilter;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.security.util.TokenUtil;
import com.jebms.comm.security.util.UserUtil;
import com.jebms.comm.utils.IPUtils;
import com.jebms.fnd.entity.FndLogin;
import com.jebms.fnd.entity.FndUserVO;
import com.jebms.fnd.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Authentication controller.
 *
 * @author samt007@qq.com
 */
@RestController
@RequestMapping("/auth")
@Api(value = "登录接口",description="登录接口，获取或者更新Token调用")
public class AuthController {
	
    @Autowired
    private SystemService systemService;
    /**
     * 权限管理
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 用户信息服务
     */
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * Token工具
     */
    @Autowired
    private TokenUtil jwtTokenUtil;

    /**
     * Create authentication token bearer auth token.
     *
     * @param user the fnd user
     * @return the bearer auth token
     */
    @PostMapping(value = "/token")
    @ApiOperation(value="获取认证token接口")
    public Map<String, Object> createAuthenticationToken(HttpServletRequest request,@RequestBody FndUserVO user) {
    	// Perform the security
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //产生登录信息
        AuthUser authUser=(AuthUser) userDetails;//认证的用户
        //System.out.println("userDetails:"+authUser.getNicename()+",userId:"+authUser.getId());
        //System.out.println(new Gson().toJson(userDetails));
    	FndLogin login=new FndLogin();
    	login.setUserId(authUser.getId());
    	login.setStartDate(new Date());
    	login.setLanguage(user.getLanguage()==null?"ZHS":user.getLanguage());
    	login.setIpAddress(IPUtils.getIpAddr(request));
    	int recs=systemService.insertLogin(login);
    	if(recs==1){//需要记录登录信息
    		authUser.setLoginId(login.getId());
    		authUser.setLanguage(login.getLanguage());
    	}else{
    		throw new RuntimeException(String.format("产生登录信息失败！"));
    	}
        final String token = jwtTokenUtil.generateToken(request,userDetails);
        // Return the token
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", token);
        tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
        tokenMap.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);
        tokenMap.put("authUser", authUser);
        return tokenMap;
    }

    /**
     * Refresh and get authentication token bearer auth token.
     *
     * @param request the request
     * @return the bearer auth token
     */
    @GetMapping(value = "/refresh")
    @ApiOperation(value="更新认证token接口")
    public Map<String, Object> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        //注意：刷新不改变登录id信息！
        AuthUser user=UserUtil.getCurrentUser();
        FndLogin login=systemService.selectLoginById(user.getLoginId());
        //处理更新认证token
        String tokenHeader = request.getHeader(AuthenticationTokenFilter.TOKEN_HEADER);
        String token = tokenHeader.split(" ")[1];
        String username = jwtTokenUtil.getUsernameFromToken(token);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        ((AuthUser)userDetails).setLoginId(login.getId());
        ((AuthUser)userDetails).setLanguage(login.getLanguage());
        final String refreshedToken = jwtTokenUtil.generateToken(request,userDetails);
        System.out.println("refresh user:"+new Gson().toJson(userDetails));
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", refreshedToken);
        tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
        tokenMap.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);
        tokenMap.put("authUser", (AuthUser)userDetails);
        return tokenMap;
    }

    /**
     * Delete authentication token response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/token")
    @ApiOperation(value="删除认证token接口")
    public ResultEntity deleteAuthenticationToken(HttpServletRequest request) {
        //更新登录信息
        AuthUser user=UserUtil.getCurrentUser();
        FndLogin login=systemService.selectLoginById(user.getLoginId());
        login.setEndDate(new Date());
        systemService.updateLogin(login);
        //处理删除认证
        String tokenHeader = request.getHeader(AuthenticationTokenFilter.TOKEN_HEADER);
        String token = tokenHeader.split(" ")[1];
        jwtTokenUtil.removeToken(request,token);
        return ResultInfo.success();//new ResponseEntity(HttpStatus.OK);
    }

}
