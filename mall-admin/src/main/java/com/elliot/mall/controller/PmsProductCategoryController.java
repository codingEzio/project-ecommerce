package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.dto.PmsProductCategoryParam;
import com.elliot.mall.dto.PmsProductCategoryWithChildrenItem;
import com.elliot.mall.model.PmsProductCategory;
import com.elliot.mall.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "PmsProductCategoryController")
@Tag(name = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
	@Autowired
	private PmsProductCategoryService productCategoryService;

	@ApiOperation("添加商品分类")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
		int count = productCategoryService.create(productCategoryParam);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改商品分类")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(@PathVariable Long id,
			@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
		int count = productCategoryService.update(id, productCategoryParam);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("分页查询商品分类")
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<PmsProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
		return CommonResult.success(CommonPage.restPage(productCategoryList));
	}

	@ApiOperation("根据id获取商品分类")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<PmsProductCategory> getItem(@PathVariable Long id) {
		PmsProductCategory productCategory = productCategoryService.getItem(id);
		return CommonResult.success(productCategory);
	}

	@ApiOperation("删除商品分类")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@PathVariable Long id) {
		int count = productCategoryService.delete(id);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改导航栏显示状态")
	@RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids,
			@RequestParam("navStatus") Integer navStatus) {
		int count = productCategoryService.updateNavStatus(ids, navStatus);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改显示状态")
	@RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
			@RequestParam("showStatus") Integer showStatus) {
		int count = productCategoryService.updateShowStatus(ids, showStatus);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("查询所有一级分类及子分类")
	@RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
		List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
		return CommonResult.success(list);
	}
}
