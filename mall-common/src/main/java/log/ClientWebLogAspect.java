package com.elliot.mall.common.log;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.elliot.mall.common.util.RequestUtil;
import com.elliot.mall.domain.ClientWebLog;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiOperation;
import net.logstash.logback.marker.Markers;

/**
 * Aspect for logging web requests to controllers in a Spring Boot application.
 */
@Aspect
@Component
@Order(1)
public class ClientWebLogAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientWebLogAspect.class);

	/**
	 * Pointcut that matches all public methods in controllers.
	 */
	@Pointcut("execution(public * com.elliot.mall.controller.*.*(..)) || "
			+ "execution(public * com.elliot.mall.*.controller.*.*(..))")
	public void webLog() {}

	/**
	 * Logs the request information before the method execution.
	 */
	@Before("webLog()")
	public void logBefore(JoinPoint joinPoint) {
		// Do nothing for now, but this method could be used to log additional information
		// before the method execution in the future.
	}

	/**
	 * Logs the request information after the method execution.
	 */
	@AfterReturning(value = "webLog()", returning = "ret")
	public void logAfterReturning(Object ret) {
		// Do nothing for now, but this method could be used to log additional information
		// after the method execution in the future.
	}

	/**
	 * Logs the request information before and after the method execution.
	 */
	@Around("webLog()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		ClientWebLogAspect webLog = new ClientWebLogAspect();

		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method.isAnnotationPresent(ApiOperation.class)) {
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			webLog.setDescription(apiOperation.value());
		}

		String urlStr = request.getRequestURL().toString();
		webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
		webLog.setUsername(request.getRemoteUser());
		webLog.setIp(RequestUtil.getRequestIp(request));
		webLog.setMethod(request.getMethod());
		webLog.setParameter(getParameters(method, joinPoint.getArgs()));

		Object result = joinPoint.proceed();
		webLog.setResult(result);

		long endTime = System.currentTimeMillis();
		webLog.setSpendTime((int) (endTime - startTime));
		webLog.setStartTime(startTime);
		webLog.setUri(request.getRequestURI());
		webLog.setUrl(request.getRequestURL().toString());

		Map<String,Object> logMap = new HashMap<>();
		logMap.put("url",webLog.getUrl());
		logMap.put("method",webLog.getMethod());
		logMap.put("parameter",webLog.getParameter());
		logMap.put("spendTime",webLog.getSpendTime());
		logMap.put("description",webLog.getDescription());

		LOGGER.info(Markers.appendEntries(logMap), JSONUtil.toJsonStr(webLog));
		return result;
	}

	/**
	 * Extracts the request parameters from the method arguments.
	 */
	private Object getParameters(Method method, Object[] args) {
		List<Object> parameters = new ArrayList<>();
		Parameter[] methodParameters = method.getParameters();
		for (int i = 0; i < methodParameters.length; i++) {
			RequestBody requestBody = methodParameters[i].getAnnotation(RequestBody.class);
			if (requestBody != null) {
				parameters.add(args[i]);
			}
			RequestParam requestParam = methodParameters[i].getAnnotation(RequestParam.class);
			if (requestParam != null) {
				Map<String, Object> paramMap = new HashMap<>();
				String key = methodParameters[i].getName();
				if (!StrUtil.isEmpty(requestParam.value())) {
					key = requestParam.value();
				}
				if (args[i] != null) {
					paramMap.put(key, args[i]);
					parameters.add(paramMap);
				}
			}
		}
		return parameters.size() == 0 ? null : parameters.size() == 1 ? parameters.get(0) : parameters;
	}
