package com.jebms.comm.security.log.aop;


//import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.security.util.UserUtil;
import com.jebms.comm.utils.IPUtils;
//import com.jebms.fnd.entity.FndLog;
//import com.jebms.fnd.service.FndLogService;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;


/**
 * 系统日志，切面处理类
 *
 * @author samt007@qq.com
 */
@Aspect
@Component
public class FndLogAspect {

    @Autowired
    //private FndLogService fndLogService; 这个到时候可以考虑直接写insert语句来进行新增数据！

    private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(FndLogAspect.class);

    // 开始时间
    private long startTime = 0L;
    // 结束时间
    private long endTime = 0L;

    @Before("execution(* *..controller..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        _log.debug("doBeforeInServiceLayer");
        startTime = System.currentTimeMillis();
    }

    @After("execution(* *..controller..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        _log.debug("doAfterInServiceLayer");
    }

    //@SuppressWarnings("deprecation")
	@Around("execution(* *..controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
        /*
    	// 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        FndLog fndLog = new FndLog();
        // 从注解中获取操作名称、获取响应结果
        Object result = pjp.proceed();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            fndLog.setDescription(log.value());
        }
        if (method.isAnnotationPresent(PreAuthorize.class)) {
            PreAuthorize requiresPermissions = method.getAnnotation(PreAuthorize.class);
            String permissions = requiresPermissions.value();
            fndLog.setPermissions(permissions);
        }
        endTime = System.currentTimeMillis();
        _log.debug("doAround>>>result={},耗时：{}", result, endTime - startTime);

        fndLog.setBasePath(IPUtils.getBasePath(request));
        fndLog.setIp(IPUtils.getIpAddr(request));
        fndLog.setMethod(request.getMethod());
        if (request.getMethod().equalsIgnoreCase("GET")) {
        	fndLog.setParameter(request.getQueryString());
        } else {
        	fndLog.setParameter(ObjectUtils.toString(request.getParameterMap()));
        }
        fndLog.setResult(ObjectUtils.toString(result));
        fndLog.setSpendTime((int) (endTime - startTime));
        fndLog.setStartTime(startTime);
        fndLog.setUri(request.getRequestURI());
        fndLog.setUrl(ObjectUtils.toString(request.getRequestURL()));
        fndLog.setUserAgent(request.getHeader("User-Agent"));
        if(request.getUserPrincipal()!=null) {
        	fndLog.setUserName(ObjectUtils.toString(request.getUserPrincipal().getName()));
        }
        AuthUser user=UserUtil.getCurrentUser();
        fndLog.setWhoInsert(user.getId(), user.getLoginId());
        fndLogService.insert(fndLog);
        return result;*/
    }
}
