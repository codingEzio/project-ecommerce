package com.elliot.mall.portal.service;

import com.elliot.mall.portal.domain.OmsOrderReturnApplyParam;

public interface OmsPortalOrderReturnApplyService {
	/**
	 * 提交申请
	 */
	int create(OmsOrderReturnApplyParam returnApply);
}
