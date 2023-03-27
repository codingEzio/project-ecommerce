package com.elliot.mall.service;

import com.elliot.mall.model.OmsOrderSetting;

public interface OmsOrderSettingService {
	OmsOrderSetting getItem(Long id);

	int update(Long id, OmsOrderSetting orderSetting);
}
