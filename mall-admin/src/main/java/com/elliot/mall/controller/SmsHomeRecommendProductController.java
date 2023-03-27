package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.model.SmsHomeRecommendProduct;
import com.elliot.mall.service.SmsHomeRecommendProductService;
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
@Api(tags = "SmsHomeRecommendProductController")
@Tag(name = "SmsHomeRecommendProductController", description = "首页人气推荐管理")
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {
	@Autowired
	private SmsHomeRecommendProductService recommendProductService;

	@ApiOperation("添加首页推荐")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody List<SmsHomeRecommendProduct> homeRecommendProductList) {
		int count = recommendProductService.create(homeRecommendProductList);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("修改推荐排序")
	@RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateSort(@PathVariable Long id, Integer sort) {
		int count = recommendProductService.updateSort(id, sort);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("批量删除推荐")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam("ids") List<Long> ids) {
		int count = recommendProductService.delete(ids);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("批量修改推荐状态")
	@RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
			@RequestParam Integer recommendStatus) {
		int count = recommendProductService.updateRecommendStatus(ids, recommendStatus);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("分页查询推荐")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<SmsHomeRecommendProduct>> list(
			@RequestParam(value = "productName", required = false) String productName,
			@RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<SmsHomeRecommendProduct> homeRecommendProductList = recommendProductService.list(productName,
				recommendStatus, pageSize, pageNum);
		return CommonResult.success(CommonPage.restPage(homeRecommendProductList));
	}
}
