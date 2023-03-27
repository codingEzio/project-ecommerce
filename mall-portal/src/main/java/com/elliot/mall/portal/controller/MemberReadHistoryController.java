package com.elliot.mall.portal.controller;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.portal.domain.MemberReadHistory;
import com.elliot.mall.portal.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "MemberReadHistoryController")
@Tag(name = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
	@Autowired
	private MemberReadHistoryService memberReadHistoryService;

	@ApiOperation("创建浏览记录")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
		int count = memberReadHistoryService.create(memberReadHistory);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("删除浏览记录")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam("ids") List<String> ids) {
		int count = memberReadHistoryService.delete(ids);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("清空浏览记录")
	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult clear() {
		memberReadHistoryService.clear();
		return CommonResult.success(null);
	}

	@ApiOperation("分页获取浏览记录")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<MemberReadHistory>> list(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		Page<MemberReadHistory> page = memberReadHistoryService.list(pageNum, pageSize);
		return CommonResult.success(CommonPage.restPage(page));
	}
}
