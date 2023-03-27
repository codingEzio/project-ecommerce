package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.model.OmsOrderSetting;
import com.elliot.mall.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "OmsOrderSettingController")
@Tag(name = "OmsOrderSettingController", description = "订单设置管理")
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
	@Autowired
	private OmsOrderSettingService orderSettingService;

	@ApiOperation("获取指定订单设置")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<OmsOrderSetting> getItem(@PathVariable Long id) {
		OmsOrderSetting orderSetting = orderSettingService.getItem(id);
		return CommonResult.success(orderSetting);
	}

	@ApiOperation("修改指定订单设置")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderSetting orderSetting) {
		int count = orderSettingService.update(id, orderSetting);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}
}
