package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.dto.UmsMenuNode;
import com.elliot.mall.model.UmsMenu;
import com.elliot.mall.service.UmsMenuService;
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
@Api(tags = "UmsMenuController")
@Tag(name = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {

	@Autowired
	private UmsMenuService menuService;

	@ApiOperation("添加后台菜单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody UmsMenu umsMenu) {
		int count = menuService.create(umsMenu);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改后台菜单")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(@PathVariable Long id,
			@RequestBody UmsMenu umsMenu) {
		int count = menuService.update(id, umsMenu);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("根据ID获取菜单详情")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<UmsMenu> getItem(@PathVariable Long id) {
		UmsMenu umsMenu = menuService.getItem(id);
		return CommonResult.success(umsMenu);
	}

	@ApiOperation("根据ID删除后台菜单")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@PathVariable Long id) {
		int count = menuService.delete(id);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("分页查询后台菜单")
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
		return CommonResult.success(CommonPage.restPage(menuList));
	}

	@ApiOperation("树形结构返回所有菜单列表")
	@RequestMapping(value = "/treeList", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<UmsMenuNode>> treeList() {
		List<UmsMenuNode> list = menuService.treeList();
		return CommonResult.success(list);
	}

	@ApiOperation("修改菜单显示状态")
	@RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
		int count = menuService.updateHidden(id, hidden);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}
}
