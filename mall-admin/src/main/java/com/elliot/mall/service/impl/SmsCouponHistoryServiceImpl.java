package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.SmsCouponHistoryMapper;
import com.elliot.mall.model.SmsCouponHistory;
import com.elliot.mall.model.SmsCouponHistoryExample;
import com.elliot.mall.service.SmsCouponHistoryService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
	@Autowired
	private SmsCouponHistoryMapper historyMapper;

	@Override
	public List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize,
			Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		SmsCouponHistoryExample example = new SmsCouponHistoryExample();
		SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
		if (couponId != null) {
			criteria.andCouponIdEqualTo(couponId);
		}
		if (useStatus != null) {
			criteria.andUseStatusEqualTo(useStatus);
		}
		if (!StrUtil.isEmpty(orderSn)) {
			criteria.andOrderSnEqualTo(orderSn);
		}
		return historyMapper.selectByExample(example);
	}
}
