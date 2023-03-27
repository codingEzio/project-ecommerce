package com.elliot.mall.portal.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.portal.domain.PmsPortalProductDetail;
import com.elliot.mall.portal.domain.PmsProductCategoryNode;
import com.elliot.mall.portal.service.PmsPortalProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api(tags = "PmsPortalProductController")
@Tag(name = "PmsPortalProductController", description = "前台商品管理")
@RequestMapping("/product")
public class PmsPortalProductController {

	@Autowired
	private PmsPortalProductService portalProductService;

	@ApiOperation(value = "综合搜索、筛选、排序")
	@ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低", defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<PmsProduct>> search(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Long brandId,
			@RequestParam(required = false) Long productCategoryId,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(required = false, defaultValue = "0") Integer sort) {
		List<PmsProduct> productList = portalProductService.search(keyword, brandId, productCategoryId, pageNum,
				pageSize, sort);
		return CommonResult.success(CommonPage.restPage(productList));
	}

	@ApiOperation("以树形结构获取所有商品分类")
	@RequestMapping(value = "/categoryTreeList", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsProductCategoryNode>> categoryTreeList() {
		List<PmsProductCategoryNode> list = portalProductService.categoryTreeList();
		return CommonResult.success(list);
	}

	@ApiOperation("获取前台商品详情")
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
		PmsPortalProductDetail productDetail = portalProductService.detail(id);
		return CommonResult.success(productDetail);
	}
}
