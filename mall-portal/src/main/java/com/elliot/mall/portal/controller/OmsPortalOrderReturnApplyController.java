package com.elliot.mall.portal.controller;

import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.portal.domain.OmsOrderReturnApplyParam;
import com.elliot.mall.portal.service.OmsPortalOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "OmsPortalOrderReturnApplyController")
@Tag(name = "OmsPortalOrderReturnApplyController", description = "退货申请管理")
@RequestMapping("/returnApply")
public class OmsPortalOrderReturnApplyController {
	@Autowired
	private OmsPortalOrderReturnApplyService returnApplyService;

	@ApiOperation("申请退货")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody OmsOrderReturnApplyParam returnApply) {
		int count = returnApplyService.create(returnApply);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}
}
