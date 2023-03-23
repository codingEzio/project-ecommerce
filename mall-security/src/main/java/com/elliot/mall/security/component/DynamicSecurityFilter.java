package com.elliot.mall.security.component;

import com.elliot.mall.security.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This is a Spring Security filter that intercepts incoming HTTP requests and applies access control based on the security metadata source provided. The filter extends the AbstractSecurityInterceptor class, which is a base class that provides a standard way of performing security checks, and implements the Filter interface, which allows it to be used as a filter in the Servlet container.
 */
public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

	@Autowired
	private IgnoreUrlsConfig ignoreUrlsConfig;

	@Autowired
	public void setMyAccessDecisionManager(DynamicAccessDecisionManager dynamicAccessDecisionManager) {
		super.setAccessDecisionManager(dynamicAccessDecisionManager);
	}

	/**
	 * This method is called by the servlet container to indicate to a filter that it is being placed into service. In this implementation, it does not do anything.
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * In the doFilter method, the filter first checks if the request method is OPTIONS, and if so, allows the request to proceed without any security checks. It then checks if the requested URL matches any of the URLs configured in the IgnoreUrlsConfig bean, and if so, allows the request to proceed without any security checks. Otherwise, it calls the beforeInvocation method of the AbstractSecurityInterceptor class to perform the security checks and obtain an InterceptorStatusToken, which represents the security status of the request. It then allows the request to proceed by calling the doFilter method of the filter chain, and finally calls the afterInvocation method of the AbstractSecurityInterceptor class to perform any necessary cleanup.
	 *
	 * @param servletRequest
	 * @param servletResponse
	 * @param filterChain
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		FilterInvocation fltInvo = new FilterInvocation(servletRequest, servletResponse, filterChain);

		if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
			fltInvo.getChain().doFilter(fltInvo.getRequest(), fltInvo.getResponse());

			return;
		}

		PathMatcher pathMatcher = new AntPathMatcher();
		for (String path: ignoreUrlsConfig.getUrls()) {
			if (pathMatcher.match(path, request.getRequestURI())) {
				fltInvo.getChain().doFilter(fltInvo.getRequest(), fltInvo.getResponse());

				return;
			}
		}

		InterceptorStatusToken token = super.beforeInvocation(fltInvo);
		try {
			fltInvo.getChain().doFilter(fltInvo.getRequest(), fltInvo.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	/**
	 * This method is called by the servlet container to indicate to a filter that it is being taken out of service. In this implementation, it does not do anything.
	 */
	@Override
	public void destroy() {
	}

	/**
	 * This method is used to obtain the class of the secured object being invoked. In this case, it returns FilterInvocation.class.
	 */
	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	/**
	 * This method is used to obtain the SecurityMetadataSource for this filter. In this implementation, it returns the DynamicSecurityMetadataSource instance that was autowired.
	 */
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return dynamicSecurityMetadataSource;
	}

}
