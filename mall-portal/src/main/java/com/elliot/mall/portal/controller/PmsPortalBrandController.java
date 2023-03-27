package com.elliot.mall.portal.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.model.PmsBrand;
import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.portal.service.PmsPortalBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "PmsPortalBrandService")
@Tag(name = "PmsPortalBrandService", description = "前台品牌管理")
@RequestMapping("/brand")
public class PmsPortalBrandController {

	@Autowired
	private PmsPortalBrandService portalBrandService;

	@ApiOperation("分页获取推荐品牌")
	@RequestMapping(value = "/recommendList", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsBrand>> recommendList(
			@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<PmsBrand> brandList = portalBrandService.recommendList(pageNum, pageSize);
		return CommonResult.success(brandList);
	}

	@ApiOperation("获取品牌详情")
	@RequestMapping(value = "/detail/{brandId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<PmsBrand> detail(@PathVariable Long brandId) {
		PmsBrand brand = portalBrandService.detail(brandId);
		return CommonResult.success(brand);
	}

	@ApiOperation("分页获取品牌相关商品")
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<PmsProduct>> productList(@RequestParam Long brandId,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
		CommonPage<PmsProduct> result = portalBrandService.productList(brandId, pageNum, pageSize);
		return CommonResult.success(result);
	}
}
