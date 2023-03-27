package com.elliot.mall.portal.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.OmsOrderReturnApplyMapper;
import com.elliot.mall.model.OmsOrderReturnApply;
import com.elliot.mall.portal.domain.OmsOrderReturnApplyParam;
import com.elliot.mall.portal.service.OmsPortalOrderReturnApplyService;

@Service
public class OmsPortalOrderReturnApplyServiceImpl implements OmsPortalOrderReturnApplyService {
	@Autowired
	private OmsOrderReturnApplyMapper returnApplyMapper;

	@Override
	public int create(OmsOrderReturnApplyParam returnApply) {
		OmsOrderReturnApply realApply = new OmsOrderReturnApply();
		BeanUtils.copyProperties(returnApply, realApply);
		realApply.setCreateTime(new Date());
		realApply.setStatus(0);
		return returnApplyMapper.insert(realApply);
	}
}
