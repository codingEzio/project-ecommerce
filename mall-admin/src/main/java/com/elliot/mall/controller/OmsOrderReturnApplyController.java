package com.elliot.mall.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.dto.OmsOrderReturnApplyResult;
import com.elliot.mall.dto.OmsReturnApplyQueryParam;
import com.elliot.mall.dto.OmsUpdateStatusParam;
import com.elliot.mall.model.OmsOrderReturnApply;
import com.elliot.mall.service.OmsOrderReturnApplyService;
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
@Api(tags = "OmsOrderReturnApplyController")
@Tag(name = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
	@Autowired
	private OmsOrderReturnApplyService returnApplyService;

	@ApiOperation("分页查询退货申请")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<OmsOrderReturnApply>> list(OmsReturnApplyQueryParam queryParam,
	                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
	                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<OmsOrderReturnApply> returnApplyList = returnApplyService.list(queryParam, pageSize, pageNum);
		return CommonResult.success(CommonPage.restPage(returnApplyList));
	}

	@ApiOperation("批量删除退货申请")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam("ids") List<Long> ids) {
		int count = returnApplyService.delete(ids);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

	@ApiOperation("获取退货申请详情")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult getItem(@PathVariable Long id) {
		OmsOrderReturnApplyResult result = returnApplyService.getItem(id);
		return CommonResult.success(result);
	}

	@ApiOperation("修改退货申请状态")
	@RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
		int count = returnApplyService.updateStatus(id, statusParam);
		if (count > 0) {
			return CommonResult.success(count);
		}
		return CommonResult.failed();
	}

}
