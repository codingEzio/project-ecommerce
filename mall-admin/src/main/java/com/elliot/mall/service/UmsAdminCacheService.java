package com.elliot.mall.service;

import com.elliot.mall.model.UmsAdmin;
import com.elliot.mall.model.UmsResource;

import java.util.List;

/**
 * Interface for performing caching operations for UmsAdmin and UmsResource
 * objects.
 */
public interface UmsAdminCacheService {

	/**
	 * Deletes cached UmsAdmin object for provided admin id.
	 *
	 * @param adminId id of admin to delete from cache
	 */
	void delAdmin(Long adminId);

	/**
	 * Deletes cached UmsResource list object for provided admin id.
	 *
	 * @param adminId id of admin whose resource list will be deleted from cache
	 */
	void delResourceList(Long adminId);

	/**
	 * Deletes cached UmsResource list object for provided role id.
	 *
	 * @param roleId id of role whose resource list will be deleted from cache
	 */
	void delResourceListByRole(Long roleId);

	/**
	 * Deletes cached UmsResource list object for provided list of role ids.
	 *
	 * @param roleIds list of role ids whose resource lists will be deleted from
	 *                cache
	 */
	void delResourceListByRoleIds(List<Long> roleIds);

	/**
	 * Deletes cached UmsResource list object for provided resource id.
	 *
	 * @param resourceId id of resource whose resource list will be deleted from
	 *                   cache
	 */
	void delResourceListByResource(Long resourceId);

	/**
	 * Returns cached UmsAdmin object for provided username.
	 *
	 * @param username username of admin to retrieve from cache
	 * @return cached UmsAdmin object for provided username
	 */
	UmsAdmin getAdmin(String username);

	/**
	 * Sets cached UmsAdmin object.
	 *
	 * @param admin UmsAdmin object to be cached
	 */
	void setAdmin(UmsAdmin admin);

	/**
	 * Returns cached UmsResource list object for provided admin id.
	 *
	 * @param adminId id of admin whose UmsResource list should be retrieved from
	 *                cache
	 * @return cached UmsResource list for provided admin id
	 */
	List<UmsResource> getResourceList(Long adminId);

	/**
	 * Sets cached UmsResource list object for provided admin id.
	 *
	 * @param adminId      id of admin whose resource list should be cached
	 * @param resourceList UmsResource list object to be cached
	 */
	void setResourceList(Long adminId, List<UmsResource> resourceList);
}
