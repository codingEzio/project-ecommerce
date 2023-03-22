package com.elliot.mall.common.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * An utility class for handling HTTP requests.
 */
public class RequestUtil {

	/**
	 * Gets the real IP address of the request.
	 */
	public static String getRequestIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			// If accessed locally, use the IP configured on the local network card
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				InetAddress inetAddress = null;
				try {
					inetAddress = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				assert inetAddress != null;
				ipAddress = inetAddress.getHostAddress();
			}
		}
		// In case of multiple proxy forwarding, the first IP is the real client IP,
		// multiple IPs are separated by commas
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

}
