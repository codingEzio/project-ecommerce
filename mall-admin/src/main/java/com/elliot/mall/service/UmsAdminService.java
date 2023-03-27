package com.elliot.mall.service;

import com.elliot.mall.dto.UmsAdminParam;
import com.elliot.mall.dto.UpdateAdminPasswordParam;
import com.elliot.mall.model.UmsAdmin;
import com.elliot.mall.model.UmsResource;
import com.elliot.mall.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UmsAdminService {

	// retrieves an administrative user by their username
	UmsAdmin getAdminByUsername(String username);

	// registers a new administrative user with the given parameters
	UmsAdmin register(UmsAdminParam umsAdminParam);

	/**
	 * Logs in an administrative user with the given username and password.
	 *
	 * @param username the username of the user to log in
	 * @param password the password of the user to log in
	 * @return the generated JWT token
	 */
	String login(String username, String password);

	/**
	 * Refreshes the token associated with the given old token.
	 *
	 * @param oldToken the old token to refresh
	 * @return the new token generated after refreshing
	 */
	String refreshToken(String oldToken);

	// retrieves an administrative user by their ID
	UmsAdmin getItem(Long id);

	/**
	 * Retrieves a list of administrative users with usernames or nicknames that
	 * match the given keyword.
	 *
	 * @param keyword  the keyword to search for in usernames and nicknames
	 * @param pageSize the maximum number of results to return per page
	 * @param pageNum  the page number to return
	 * @return a list of administrative users matching the search criteria
	 */
	List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

	/**
	 * Updates the information for the specified administrative user.
	 *
	 * @param id    the ID of the administrative user to update
	 * @param admin the updated information for the administrative user
	 * @return the number of rows affected by the update
	 */
	int update(Long id, UmsAdmin admin);

	/**
	 * Deletes the administrative user with the given ID.
	 *
	 * @param id the ID of the administrative user to delete
	 * @return the number of rows affected by the delete
	 */
	int delete(Long id);

	/**
	 * Updates the relationship between the specified administrative user and the
	 * given list of roles.
	 *
	 * @param adminId the ID of the administrative user to update
	 * @param roleIds the list of role IDs to associate with the administrative user
	 * @return the number of rows affected by the update
	 */
	@Transactional
	int updateRole(Long adminId, List<Long> roleIds);

	/**
	 * Retrieves a list of roles associated with the specified administrative user.
	 *
	 * @param adminId the ID of the administrative user to retrieve roles for
	 * @return a list of roles associated with the administrative user
	 */
	List<UmsRole> getRoleList(Long adminId);

	/**
	 * Retrieves a list of resources accessible by the specified administrative
	 * user.
	 *
	 * @param adminId the ID of the administrative user to retrieve resources for
	 * @return a list of resources accessible by the administrative user
	 */
	List<UmsResource> getResourceList(Long adminId);

	/**
	 * Updates the password for the specified administrative user.
	 *
	 * @param updatePasswordParam the new password for the administrative user
	 * @return the number of rows affected by the update
	 */
	int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

	/**
	 * Retrieves information for the administrative user with the specified
	 * username.
	 *
	 * @param username the username of the administrative user to retrieve
	 *                 information for
	 * @return the administrative user details
	 */
	UserDetails loadUserByUsername(String username);

	/**
	 * Retrieves the cache service for the administrative user.
	 *
	 * @return the cache
	 */
	UmsAdminCacheService getCacheService();
}
