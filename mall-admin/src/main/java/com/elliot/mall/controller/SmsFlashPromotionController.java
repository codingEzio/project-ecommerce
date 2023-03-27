package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.model.SmsFlashPromotion;
import com.elliot.mall.service.SmsFlashPromotionService;
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
@Api(tags = "SmsFlashPromotionController")
@Tag(name = "SmsFlashPromotionController", description = "限时购活动管理")
@RequestMapping("/flash")
public class SmsFlashPromotionController {
	@Autowired
	private SmsFlashPromotionService flashPromotionService;

	@ApiOperation("添加活动")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody SmsFlashPromotion flashPromotion) {
		int count = flashPromotionService.create(flashPromotion);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("编辑活动")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable Long id, @RequestBody SmsFlashPromotion flashPromotion) {
		int count = flashPromotionService.update(id, flashPromotion);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("删除活动")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@PathVariable Long id) {
		int count = flashPromotionService.delete(id);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("修改上下线状态")
	@RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable Long id, Integer status) {
		int count = flashPromotionService.updateStatus(id, status);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("获取活动详情")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getItem(@PathVariable Long id) {
		SmsFlashPromotion flashPromotion = flashPromotionService.getItem(id);
		return CommonResult.success(flashPromotion);
	}

	@ApiOperation("根据活动名称分页查询")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object getItem(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<SmsFlashPromotion> flashPromotionList = flashPromotionService.list(keyword, pageSize, pageNum);
		return CommonResult.success(CommonPage.restPage(flashPromotionList));
	}
}
