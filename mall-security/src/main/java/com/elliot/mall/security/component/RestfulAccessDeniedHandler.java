package com.elliot.mall.security.component;

import cn.hutool.json.JSONUtil;
import com.macro.mall.common.api.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is a class implementation of the Spring Security AccessDeniedHandler interface. It handles an AccessDeniedException that is thrown when an authenticated user tries to access a resource that they don't have permission to access.
 * <p>
 * When this happens, the handle method is called and it sets some headers and content type for the HTTP response. Then, it uses the JSONUtil from the Hutool library to parse a CommonResult object with a forbidden message and writes it to the response body.
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request,
	                   HttpServletResponse response,
	                   AccessDeniedException e) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control", "no-cache");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
		response.getWriter().flush();
	}
}
