package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.model.SmsHomeNewProduct;
import com.elliot.mall.service.SmsHomeNewProductService;
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
@Api(tags = "SmsHomeNewProductController")
@Tag(name = "SmsHomeNewProductController", description = "首页新品管理")
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {
	@Autowired
	private SmsHomeNewProductService homeNewProductService;

	@ApiOperation("添加首页新品")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody List<SmsHomeNewProduct> homeNewProductList) {
		int count = homeNewProductService.create(homeNewProductList);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("修改首页新品排序")
	@RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateSort(@PathVariable Long id, Integer sort) {
		int count = homeNewProductService.updateSort(id, sort);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("批量删除首页新品")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam("ids") List<Long> ids) {
		int count = homeNewProductService.delete(ids);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("批量修改首页新品状态")
	@RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
			@RequestParam Integer recommendStatus) {
		int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("分页查询首页新品")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<SmsHomeNewProduct>> list(
			@RequestParam(value = "productName", required = false) String productName,
			@RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<SmsHomeNewProduct> homeNewProductList = homeNewProductService.list(productName, recommendStatus, pageSize,
				pageNum);
		return CommonResult.success(CommonPage.restPage(homeNewProductList));
	}
}
