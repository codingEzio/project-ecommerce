package com.elliot.mall.demo.bo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.elliot.mall.model.UmsAdmin;

public class AdminUserDetails implements UserDetails {
	private UmsAdmin umsAdmin;

	public AdminUserDetails(UmsAdmin umsAdmin) {
		this.umsAdmin = umsAdmin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 返回当前用户的权限
		return Arrays.asList(new SimpleGrantedAuthority("TEST"));
	}

	@Override
	public String getPassword() {
		return umsAdmin.getPassword();
	}

	@Override
	public String getUsername() {
		return umsAdmin.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
