package com.elliot.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.OmsOrderSettingMapper;
import com.elliot.mall.model.OmsOrderSetting;
import com.elliot.mall.service.OmsOrderSettingService;

@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
	@Autowired
	private OmsOrderSettingMapper orderSettingMapper;

	@Override
	public OmsOrderSetting getItem(Long id) {
		return orderSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(Long id, OmsOrderSetting orderSetting) {
		orderSetting.setId(id);
		return orderSettingMapper.updateByPrimaryKey(orderSetting);
	}
}
