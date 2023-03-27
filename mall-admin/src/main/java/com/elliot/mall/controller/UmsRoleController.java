package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.model.UmsMenu;
import com.elliot.mall.model.UmsResource;
import com.elliot.mall.model.UmsRole;
import com.elliot.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "UmsRoleController")
@Tag(name = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {

	@Autowired
	private UmsRoleService roleService;

	/**
	 * Creates a new role
	 *
	 * @param role The role object to create
	 *
	 * @return A CommonResult object containing the result of the create operation
	 */
	@ApiOperation("添加角色")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody UmsRole role) {
		int count = roleService.create(role);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	/**
	 * Updates a role by ID
	 *
	 * @param id   The ID of the role to update
	 * @param role The updated role object
	 *
	 * @return A CommonResult object containing the result of the update operation
	 */
	@ApiOperation("修改角色")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(@PathVariable Long id, @RequestBody UmsRole role) {
		int count = roleService.update(id, role);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	/**
	 * Deletes multiple roles by ID
	 *
	 * @param ids A list of role IDs to delete
	 *
	 * @return A CommonResult object containing the result of the delete operation
	 */
	@ApiOperation("批量删除角色")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam("ids") List<Long> ids) {
		int count = roleService.delete(ids);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	/**
	 * Retrieves a list of all roles
	 *
	 * @return A CommonResult object containing the list of all roles
	 */
	@ApiOperation("获取所有角色")
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<UmsRole>> listAll() {
		List<UmsRole> roleList = roleService.list();
		return CommonResult.success(roleList);
	}

	/**
	 * Retrieves a paged list of roles by role name
	 *
	 * @param keyword  The name of the role to search for
	 * @param pageSize The maximum number of results per page
	 * @param pageNum  The page number to retrieve
	 *
	 * @return A CommonResult object containing the paged list of roles
	 */
	@ApiOperation("根据角色名称分页获取角色列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
	                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
	                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsRole> roleList = roleService.list(keyword, pageSize, pageNum);
		return CommonResult.success(CommonPage.restPage(roleList));
	}

	/**
	 * Updates the status of a role by ID
	 *
	 * @param id     The ID of the role to update
	 * @param status The new status of the role
	 *
	 * @return A CommonResult object containing the result of the update operation
	 */
	@ApiOperation("修改角色状态")
	@RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
		UmsRole umsRole = new UmsRole();
		umsRole.setStatus(status);
		int count = roleService.update(id, umsRole);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	/**
	 * Retrieves a list of all menus associated with a role
	 *
	 * @param roleId The ID of the role to retrieve menus for
	 *
	 * @return A CommonResult object containing the list of menus associated with
	 * the role
	 */
	@ApiOperation("获取角色相关菜单")
	@RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<UmsMenu>> listMenu(@PathVariable Long roleId) {
		List<UmsMenu> roleList = roleService.listMenu(roleId);
		return CommonResult.success(roleList);
	}

	/**
	 * Retrieves a list of all resources associated with a role
	 *
	 * @param roleId The ID of the role to retrieve resources for
	 *
	 * @return A CommonResult object containing the list of resources associated
	 * with the role
	 */
	@ApiOperation("获取角色相关资源")
	@RequestMapping(value = "/listResource/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<UmsResource>> listResource(@PathVariable Long roleId) {
		List<UmsResource> roleList = roleService.listResource(roleId);
		return CommonResult.success(roleList);
	}

	/**
	 * Allocates a list of menus to a role
	 *
	 * @param roleId  The ID of the role to allocate menus to
	 * @param menuIds A list of menu IDs to allocate to the role
	 *
	 * @return A CommonResult object containing the result of the allocation
	 * operation
	 */
	@ApiOperation("给角色分配菜单")
	@RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
		int count = roleService.allocMenu(roleId, menuIds);
		return CommonResult.success(count);
	}

	/**
	 * Allocates a list of resources to a role
	 *
	 * @param roleId      The ID of the role to allocate resources to
	 * @param resourceIds A list of resource IDs to allocate to the role
	 *
	 * @return A CommonResult object containing the result of the allocation
	 * operation
	 */
	@ApiOperation("给角色分配资源")
	@RequestMapping(value = "/allocResource", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
		int count = roleService.allocResource(roleId, resourceIds);
		return CommonResult.success(count);
	}
}
