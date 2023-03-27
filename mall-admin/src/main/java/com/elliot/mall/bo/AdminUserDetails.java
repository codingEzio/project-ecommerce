package com.elliot.mall.bo;

import com.elliot.mall.model.UmsAdmin;
import com.elliot.mall.model.UmsResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AdminUserDetails represents a user details object for an admin user.
 * It implements the UserDetails interface to provide user authentication and authorization details.
 */
public class AdminUserDetails implements UserDetails {
	private final UmsAdmin umsAdmin;
	private final List<UmsResource> resourceList;

	public AdminUserDetails(UmsAdmin umsAdmin, List<UmsResource> resourceList) {
		this.umsAdmin = umsAdmin;
		this.resourceList = resourceList;
	}

	/**
	 * Returns the authorities (roles) associated with this user.
	 *
	 * @return a collection of SimpleGrantedAuthority objects representing the roles of this user.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Returns the user's roles, represented as SimpleGrantedAuthority objects.
		return resourceList.stream()
				.map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
				.collect(Collectors.toList());
	}


	@Override
	public String getUsername() {
		return umsAdmin.getUsername();
	}

	@Override
	public String getPassword() {
		return umsAdmin.getPassword();
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

	/**
	 * Returns whether the user's account is enabled or not.
	 *
	 * @return true if the account is enabled, false otherwise.
	 */
	@Override
	public boolean isEnabled() {
		return umsAdmin.getStatus().equals(1);
	}
}
