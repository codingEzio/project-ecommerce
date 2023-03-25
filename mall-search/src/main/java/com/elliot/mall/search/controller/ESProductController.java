package com.elliot.mall.search.controller;

import com.elliot.mall.search.domain.EsProduct;
import com.elliot.mall.search.domain.EsProductRelatedInfo;
import com.elliot.mall.search.service.EsProductService;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "EsProductController")
@Tag(name = "EsProductController", description = "Product Search Management")
@RequestMapping("/esProduct")
public class ESProductController {
	@Autowired
	private EsProductService productService;

	@ApiOperation(value = "Import all product items into Elasticsearch")
	@RequestMapping(value = "/importAll", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Integer> importAllList() {
		int count = productService.importAll();

		return CommonResult.success(count);
	}

	@ApiOperation(value = "Create products based on product ID")
	@RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<EsProduct> create(@PathVariable Long id) {
		EsProduct product = productService.create(id);

		if (product != null) {
			return CommonResult.success(product);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation(value = "Simple search")
	@RequestMapping(value = "/search/simple", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
	                                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
	                                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
		Page<EsProduct> productPage = productService.search(keyword, pageNum, pageSize);

		return CommonResult.success(CommonPage.restPage(productPage));
	}

	@ApiOperation(value = "Comprehensive search, filtering and sorting")
	@ApiImplicitParam(name = "sort", value = "Sort by: 0->Relevance; 1->Newest; 2->Sales; 3->Price low to high; 4->Price high to low", defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
	                                                  @RequestParam(required = false) Long brandID,
	                                                  @RequestParam(required = false) Long productCategoryID,
	                                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
	                                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize,
	                                                  @RequestParam(required = false, defaultValue = "0") Integer sort) {
		Page<EsProduct> productPage = productService.search(keyword, brandID, productCategoryID, pageNum, pageSize,
				sort);

		return CommonResult.success(CommonPage.restPage(productPage));
	}

	@ApiOperation(value = "Recommend products based on product ID")
	@RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<EsProduct>> recommend(@PathVariable Long id,
	                                                     @RequestParam(required = false, defaultValue = "0") Integer pageNum,
	                                                     @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
		Page<EsProduct> productPage = productService.recommend(id, pageNum, pageSize);

		return CommonResult.success(CommonPage.restPage(productPage));
	}

	@ApiOperation(value = "Get revelant brand, categorization and attributes")
	@RequestMapping(value = "/search/relate", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
		EsProductRelatedInfo productRelatedInfo = productService.searchRelatedInfo(keyword);

		return CommonResult.success(productRelatedInfo);
	}

	@ApiOperation(value = "Delete product by ID")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> delete(@PathVariable Long id) {
		productService.delete(id);

		return CommonResult.success(null);
	}

	@ApiOperation(value = "Batch delete products by ID")
	@RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
		productService.delete(ids);

		return CommonResult.success(null);
	}
}
