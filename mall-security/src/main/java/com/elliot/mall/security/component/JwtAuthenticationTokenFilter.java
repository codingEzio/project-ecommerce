package com.elliot.mall.security.component;

import com.elliot.mall.security.util.JWTTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is a Spring Security filter used for authentication. It intercepts every HTTP request and checks if there is a valid JWT (JSON Web Token) in the request header. If there is a valid token, it extracts the username from the token, loads the corresponding user details from the database using the injected UserDetailsService, and sets the user details and authorities to the current SecurityContext. This filter is executed once per request, hence the name OncePerRequestFilter.
 * <p>
 * The JWT token is obtained from the request header, specifically from the tokenHeader and tokenHead fields, which are specified in the application configuration file (application.properties or application.yml). The tokenHeader field specifies the name of the header that carries the JWT token, while the tokenHead field specifies the prefix of the JWT token.
 * <p>
 * If the token is valid and the user details are successfully loaded, the filter creates a new instance of UsernamePasswordAuthenticationToken and sets it to the SecurityContext, which is then used for subsequent authentication and authorization checks in the application.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String authHeader = request.getHeader(this.tokenHeader);
		if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
			String authToken = authHeader.substring(this.tokenHead.length());
			String username = jwtTokenUtil.getUserNameFromToken(authToken);
			LOGGER.info("checking username:{}", username);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					LOGGER.info("authenticated user:{}", username);

					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		chain.doFilter(request, response);
	}
}
