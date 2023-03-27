package com.elliot.mall.service;

import com.elliot.mall.dto.OmsOrderReturnApplyResult;
import com.elliot.mall.dto.OmsReturnApplyQueryParam;
import com.elliot.mall.dto.OmsUpdateStatusParam;
import com.elliot.mall.model.OmsOrderReturnApply;

import java.util.List;

public interface OmsOrderReturnApplyService {
	List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

	int delete(List<Long> ids);

	int updateStatus(Long id, OmsUpdateStatusParam statusParam);

	OmsOrderReturnApplyResult getItem(Long id);
}
