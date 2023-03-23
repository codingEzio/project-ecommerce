package com.elliot.mall.security.component;

import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This is a component of Spring Security that provides a way to dynamically load security configurations based on the requested URL.
 * <p>
 * The DynamicSecurityMetadataSource class implements the FilterInvocationSecurityMetadataSource interface, which defines the methods required to provide security metadata based on a FilterInvocation object.
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static Map<String, ConfigAttribute> configAttributeMap = null;

	@Autowired
	private DynamicSecurityService dynamicSecurityService;

	/**
	 * The loadDataSource method is called after the component has been constructed, which loads the security configurations from a database or other data source via the DynamicSecurityService component.
	 */
	@PostConstruct
	public void loadDataSource() {
		configAttributeMap = dynamicSecurityService.loadDataSource();
	}

	/**
	 * The clearDataSource method is used to clear the security configurations from memory.
	 */
	public void clearDataSource() {
		configAttributeMap.clear();
		configAttributeMap = null;
	}

	/**
	 * The getAttributes method returns a collection of ConfigAttribute objects based on the requested URL. It retrieves the path of the requested URL, and matches it against the patterns in the security configurations loaded from the DynamicSecurityService component. It returns a list of ConfigAttribute objects that are associated with the matched pattern.
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		if (configAttributeMap == null) this.loadDataSource();

		List<ConfigAttribute> configAttributes = new ArrayList<>();

		String url = ((FilterInvocation) o).getRequestUrl();
		String path = URLUtil.getPath(url);
		PathMatcher pathMatcher = new AntPathMatcher();
		Iterator<String> iterator = configAttributeMap.keySet().iterator();

		while (iterator.hasNext()) {
			String pattern = iterator.next();
			if (pathMatcher.match(pattern, path)) {
				configAttributes.add(configAttributeMap.get(pattern));
			}
		}

		return configAttributes;
	}


	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/**
	 * The supports method returns true to indicate that the DynamicSecurityMetadataSource component can provide security metadata for any class.
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

}
