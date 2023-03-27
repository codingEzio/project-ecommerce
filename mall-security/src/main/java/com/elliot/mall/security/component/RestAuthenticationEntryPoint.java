package com.elliot.mall.security.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.elliot.mall.common.api.CommonResult;

import cn.hutool.json.JSONUtil;

/**
 * This code defines a class named RestAuthenticationEntryPoint, which implements the AuthenticationEntryPoint interface from the Spring Security framework. The AuthenticationEntryPoint interface is used to handle the scenario where an unauthenticated user tries to access a protected resource.
 * <p>
 * The commence method is the implementation of the AuthenticationEntryPoint interface's single method. It takes in a HttpServletRequest, a HttpServletResponse, and an AuthenticationException as parameters. It sends a JSON response to the client with information about the unauthorized request.
 * <p>
 * In particular, the method sets the Access-Control-Allow-Origin and Cache-Control headers on the response, sets the response's character encoding and content type, and writes a JSON string containing a CommonResult object with an unauthorized status and message to the response's output stream.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control", "no-cache");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
		response.getWriter().flush();
	}
}
