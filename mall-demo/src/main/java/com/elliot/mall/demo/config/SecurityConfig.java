package com.elliot.mall.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.elliot.mall.demo.bo.AdminUserDetails;
import com.elliot.mall.mapper.UmsAdminMapper;
import com.elliot.mall.model.UmsAdmin;
import com.elliot.mall.model.UmsAdminExample;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UmsAdminMapper umsAdminMapper;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Configures authorization
				// .antMatchers("/").access("hasRole('TEST')")//该路径需要TEST角色
				// .antMatchers("/brand/list").hasAuthority("TEST")//该路径需要TEST权限
				.antMatchers("/**").permitAll()
				.and()// Enables basic HTTP authentication
				.httpBasic()
				.realmName("/")
				.and()// Configures login page and error handling
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.and()// Configures logout page and success handling
				.logout()
				.logoutSuccessUrl("/")
				// .and()// Configures remember me functionality
				// .rememberMe()
				// .tokenValiditySeconds(6060*24)
				// .key("rememberMeKey")
				.and()// Disables CSRF protection
				.csrf()
				.disable()
				.headers()// Disables frame options
				.frameOptions()
				.disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				UmsAdminExample example = new UmsAdminExample();
				example.createCriteria().andUsernameEqualTo(username);

				List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
				if (umsAdminList != null && umsAdminList.size() > 0) {
					return new AdminUserDetails(umsAdminList.get(0));
				}

				throw new UsernameNotFoundException("用户名或密码错误");
			}
		};
	}
}
